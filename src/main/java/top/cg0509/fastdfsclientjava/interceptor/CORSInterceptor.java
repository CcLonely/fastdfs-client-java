package top.cg0509.fastdfsclientjava.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author 陈赓
 * @version 1.0
 * 解决跨域请求 拦截器
 */

@Component
public class CORSInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 添加跨域CORS
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));  //需要注意的是，如果要发送Cookie，Access-Control-Allow-Origin就不能设为星号，必须指定明确的、与请求网页一致的域名
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token,Content-Type, Access-Control-Allow-Headers,Authorization");
		response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
		return true;
	}
}
