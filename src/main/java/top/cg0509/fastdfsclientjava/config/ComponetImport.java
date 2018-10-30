package top.cg0509.fastdfsclientjava.config;

/**
 * @author 陈赓
 * @version 2018/10/30/030
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;


import com.github.tobato.fastdfs.FdfsClientConfig;


@Configuration
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class ComponetImport {
}
