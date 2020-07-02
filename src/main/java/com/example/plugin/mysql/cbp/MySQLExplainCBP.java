package com.example.plugin.mysql.cbp;

import org.zeroturnaround.bundled.javassist.ClassPool;
import org.zeroturnaround.bundled.javassist.CtClass;
import org.zeroturnaround.bundled.javassist.CtMethod;
import org.zeroturnaround.javarebel.LoggerFactory;
import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;

/**
 * MySQL执行计划插件
 *
 * @author zunrong
 */
public class MySQLExplainCBP extends JavassistClassBytecodeProcessor {

    @Override
    public void process(ClassPool cp, ClassLoader cl, CtClass ctClass) throws Exception {
        LoggerFactory.getInstance().echo("MySQLExplainCBP...");

        cp.importPackage("com.example.plugin.mysql.explain");

        CtMethod m = ctClass.getDeclaredMethod("executeInternal");

        m.insertBefore("{ MySQLExplain.explainSql(this.connection, $2); }");
    }
}
