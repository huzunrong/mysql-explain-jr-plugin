package com.example.plugin.mysql;

import com.example.plugin.mysql.cbp.MySQLExplainCBP;
import com.example.plugin.mysql.common.Config;
import org.zeroturnaround.javarebel.ClassResourceSource;
import org.zeroturnaround.javarebel.Integration;
import org.zeroturnaround.javarebel.IntegrationFactory;
import org.zeroturnaround.javarebel.Logger;
import org.zeroturnaround.javarebel.LoggerFactory;
import org.zeroturnaround.javarebel.Plugin;

/**
 * 插件注册
 *
 * @author zunrong
 */
public class MySQLExplainPlugin implements Plugin {

    private static final Logger logger = LoggerFactory.getInstance();

    @Override
    public void preinit() {
        Integration integration = IntegrationFactory.getInstance();
        ClassLoader cl = getClass().getClassLoader();
        Config.init();

        logger.echo("MySQL执行计划插件启用, 配置详情: \n" + Config.getMySQLConfTable());
        integration.addIntegrationProcessor(
                cl,
                "com.mysql.jdbc.PreparedStatement",
                new MySQLExplainCBP());
    }

    @Override
    public boolean checkDependencies(ClassLoader cl, ClassResourceSource crs) {
        return crs.getClassResource("com.mysql.jdbc.PreparedStatement") != null;
    }

    @Override
    public String getId() {
        return "mysql-explain-jr-plugin";
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getAuthor() {
        return null;
    }

    @Override
    public String getWebsite() {
        return null;
    }

    @Override
    public String getSupportedVersions() {
        return null;
    }

    @Override
    public String getTestedVersions() {
        return null;
    }
}
