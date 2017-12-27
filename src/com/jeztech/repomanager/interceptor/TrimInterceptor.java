package com.jeztech.repomanager.interceptor;

import java.util.Iterator;  
import java.util.Map;  
import java.util.Set;  
  
import org.apache.commons.lang3.StringUtils;  
  
import com.opensymphony.xwork2.ActionInvocation;  
import com.opensymphony.xwork2.interceptor.Interceptor;  
  
public class TrimInterceptor implements Interceptor {  
    private static final long serialVersionUID = -2578561479301489061L;  
  
    public void destroy() {  
    }  
  
    /*  
     * @Description:拦截所有参数,去掉参数空格 
     * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation) 
     */  
    public String intercept(ActionInvocation invocation) throws Exception {  
          
        Map map= invocation.getInvocationContext().getParameters();  
        Set<?> keys = map.keySet();  
                  Iterator<?> iters = keys.iterator();  
                while(iters.hasNext()){  
                    Object key = iters.next();  
                    Object value = map.get(key);  
                    map.put(key, transfer((String[])value));  
                }  
        return invocation.invoke();  
    }  
      
    /** 
     * @Description: 遍历参数数组里面的数据，取出空格 
     * @param params 
     * @return 
     */  
    private String[] transfer(String[] params){  
        for(int i=0;i<params.length;i++){  
            if(StringUtils.isEmpty(params[i]))continue;  
            params[i]=params[i].trim();  
        }  
        return params;  
    }  
  
    public void init() {  
  
    }  
  
}  