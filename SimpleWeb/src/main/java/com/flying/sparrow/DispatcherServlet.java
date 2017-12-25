package com.flying.sparrow;

import com.flying.sparrow.bean.Data;
import com.flying.sparrow.bean.Handler;
import com.flying.sparrow.bean.Param;
import com.flying.sparrow.bean.View;
import com.flying.sparrow.helper.*;
import com.flying.sparrow.util.JsonUtil;
import com.flying.sparrow.util.ReflectionUtil;
import org.apache.commons.lang3.StringUtils;

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
import java.util.Map;

/**
 * 请求转发器
 * Created by wangjianchun on 2017/11/9.
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化相关Helper类
        HelperLoader.init();
        //获取ServletContext对象（用于注册Servlet）
        ServletContext servletContext = servletConfig.getServletContext();
        //注册处理JSP的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
        //注册处理静态资源的默认Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");

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

            //获取Action处理器
            Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
            if(handler != null){
                //获取Controller类及其Bean实例
                Class<?> controllerClass = handler.getControllerClass();
                Object controllerBean = BeanHelper.getBean(controllerClass);

                Param param;
                if(UploadHelper.isMultipart(request)){
                    param = UploadHelper.createParam(request);
                }else{
                    param = RequestHelper.createParam(request);
                }

                Object result;
                Method actionMethod = handler.getActionMethod();
                if(param.isEmpty()){
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
                }else{
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
                }

                if(result instanceof View){
                    handleViewResult((View) result, request, response);
                }else if(result instanceof Data){
                    handleDataResult((Data)result, response);
                }
            }
        } finally {
            ServletHelper.destroy();
        }
    }

    private void handleViewResult(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

    private void handleDataResult(Data data, HttpServletResponse response) throws IOException {
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
}