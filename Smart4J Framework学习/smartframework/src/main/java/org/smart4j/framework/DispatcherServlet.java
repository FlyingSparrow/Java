package org.smart4j.framework;

import org.apache.commons.lang3.StringUtils;
import org.smart4j.framework.bean.*;
import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求转发器
 * @author wangjianchun
 * @create 2017/12/26
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化相关 Helper 类
        HelperLoader.init();

        //获取 ServletContext 对象（用于注册 Servlet）
        ServletContext servletContext = config.getServletContext();

        //注册处理 JSP 的 Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");

        //注册处理静态资源的默认 Servlet
        ServletRegistration defaultServelt = servletContext.getServletRegistration("default");
        defaultServelt.addMapping(ConfigHelper.getAppAssetPath()+"*");

        UploadHelper.init(servletContext);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletHelper.init(request, response);

        try {
            //获取请求方法与请求路径
            String requestMethod = request.getMethod().toLowerCase();
            String requestPath = request.getPathInfo();

            if(requestPath.equals("/favicon.ico")){
                return;
            }

            //获取 Action 处理器
            Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
            if(handler != null){
                //获取 Controller 类及其 Bean 实例
                Class<?> controllerClass = handler.getControllerClass();
                Object controllerBean = BeanHelper.getBean(controllerClass);

                Param param;
                if(UploadHelper.isMultipart(request)){
                    param = UploadHelper.createParam(request);
                }else{
                    param = RequestHelper.createParam(request);
                }

                Object result;
                //调用 Action 方法
                Method actionMethod = handler.getActionMethod();
                if(param.isEmpty()){
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
                }else{
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
                }

                //处理 Action 方法返回值
                if(result instanceof View){
                    handleViewResult((View) result, request, response);
                }else if(result instanceof Data){
                    handleDataResult((Data) result, response);
                }
            }
        } finally {
            ServletHelper.destroy();
        }
    }

    private void handleDataResult(Data result, HttpServletResponse response) throws IOException {
        //返回 JSON 数据
        Data data = result;
        Object model = data.getModel();
        if(model != null){
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JsonUtil.toJson(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }

    private void handleViewResult(View result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //返回 JSP 页面
        View view = result;
        String path = view.getPath();
        if(StringUtils.isNotEmpty(path)){
            if(path.startsWith("/")){
                response.sendRedirect(request.getContextPath()+path);
            }else{
                Map<String, Object> model = view.getModel();
                for(Map.Entry<String, Object> entry : model.entrySet()){
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                request.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(request, response);
            }
        }
    }
}
