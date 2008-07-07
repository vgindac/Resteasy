package org.jboss.resteasy.plugins.providers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ConsumeMime;
import javax.ws.rs.ProduceMime;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@Provider
@ProduceMime("*/*")
@ConsumeMime("*/*")
public class StringTextStar implements MessageBodyReader<Object>, MessageBodyWriter<Object>
{
   public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations)
   {
      return String.class.equals(type);
   }

   public Object readFrom(Class<Object> type,
                          Type genericType,
                          Annotation[] annotations,
                          MediaType mediaType,
                          MultivaluedMap<String, String> httpHeaders,
                          InputStream entityStream) throws IOException
   {
      return ProviderHelper.readString(entityStream);
   }

   
   public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations)
   {
      return String.class.equals(type);
   }

   public long getSize(Object o)
   {
      return o.toString().getBytes().length;
   }

   public void writeTo(Object o,
                       Class<?> type,
                       Type genericType,
                       Annotation[] annotations,
                       MediaType mediaType,
                       MultivaluedMap<String, Object> httpHeaders,
                       OutputStream entityStream) throws IOException
   {
      entityStream.write(o.toString().getBytes());
   }
}