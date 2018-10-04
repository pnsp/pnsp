package com.iexiao.pnsp.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class BeanHelper {
 
  private static final String updateTimeKey  = "updDttm";
  
  private static final String createTimeKey  = "addDttm";
 
  /**
   * null转换
   * @author lizhiyong
   * @date 2018年9月9日
   * @param target
   * @param clazz
   * @param descriptors
   */
  public static <T> void setDefaultProp(T target,Class<T> clazz) {
    PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(clazz);
    for (PropertyDescriptor propertyDescriptor : descriptors) {
      String fieldName = propertyDescriptor.getName();
      Object value;
      try {
        value = PropertyUtils.getProperty(target,fieldName );
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        throw new RuntimeException("can not set property  when get for "+ target +" and clazz "+clazz +" field "+ fieldName);
      }
      if (String.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
        try {
          PropertyUtils.setProperty(target, fieldName, "");
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
          throw new RuntimeException("can not set property when set for "+ target +" and clazz "+clazz + " field "+ fieldName);
        }
      }else if (Number.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
          try {
            BeanUtils.setProperty(target, fieldName, "0");
          } catch (Exception e) {
            throw new RuntimeException("can not set property when set for "+ target +" and clazz "+clazz + " field "+ fieldName);
          }
      }
    }
  }
  
  /**
   * 更新填充时间
   * @author lizhiyong
   * @date 2018年9月9日
   * @param target
   */
  public static <T> void onUpdate(T target){
    try {
      PropertyUtils.setProperty(target, updateTimeKey, System.currentTimeMillis());
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      return;
    }
  }
  
  private static <T> void innerDefault(T target, Class<?> clazz, PropertyDescriptor[] descriptors) {
	    for (PropertyDescriptor propertyDescriptor : descriptors) {
	      String fieldName = propertyDescriptor.getName();
	      Object value;
	      try {
	        value = PropertyUtils.getProperty(target,fieldName );
	      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
	        throw new RuntimeException("can not set property  when get for "+ target +" and clazz "+clazz +" field "+ fieldName);
	      }
	      if (String.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
	        try {
	          PropertyUtils.setProperty(target, fieldName, "");
	        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
	          throw new RuntimeException("can not set property when set for "+ target +" and clazz "+clazz + " field "+ fieldName);
	        }
	      }else if (Number.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
	          try {
	            BeanUtils.setProperty(target, fieldName, "0");
	          } catch (Exception e) {
	            throw new RuntimeException("can not set property when set for "+ target +" and clazz "+clazz + " field "+ fieldName);
	          }
	      }else if (Date.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
	          try {
	             BeanUtils.setProperty(target, fieldName, new Date(0));
	          } catch (Exception e) {
	             throw new RuntimeException("can not set property when set for "+ target +" and clazz "+clazz + " field "+ fieldName);
	          }
	      }
	    }
	  }
  
  /**
   * 新增填充时间
   * @author lizhiyong
   * @date 2018年9月9日
   * @param target
   */
  public static <T> void onInsert(T target){
	Class<?> clazz = target.getClass();
	PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(clazz);
	innerDefault(target, clazz, descriptors);
    long time = System.currentTimeMillis();
    Date date = new Date(time);
    try {
      PropertyUtils.setProperty(target, updateTimeKey, date);
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      
    }
    try {
      PropertyUtils.setProperty(target, createTimeKey, date);
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      
    }
  }
  
  /**
   * 主键uuid
   * @author lizhiyong
   * @date 2018年9月9日
   * @param target
   */
  public static <T> void setUUID(T target) {
	  String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	  try {
	      PropertyUtils.setProperty(target, "id", uuid);
	    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
	      
	    }
  }

}
