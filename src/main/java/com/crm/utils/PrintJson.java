package com.crm.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:刘守业
 * @Date: 2021/3/22 21:59
 */
public class PrintJson {

    //将boolean值解析为json串
    public static void printJsonFlag(HttpServletResponse response,boolean flag){

        Map<String,Boolean> map = new HashMap<>();
        map.put("success",flag);
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            //{"success":true}
            String json = objectMapper.writeValueAsString(map);
            response.getWriter().print(json);

        }catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //将对象解析为json串
    public static void printJsonObj(HttpServletResponse response,Object object){

        /*
         *
         * Person p
         * 	id name age
         * {"id":"?","name":"?","age":?}
         *
         * List<Person> pList
         * [{"id":"?","name":"?","age":?},{"id":"?","name":"?","age":?},{"id":"?","name":"?","age":?}...]
         *
         * Map
         * 	key value
         * {key:value}
         *
         *
         */
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(object);
            response.getWriter().print(json);
        }catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
