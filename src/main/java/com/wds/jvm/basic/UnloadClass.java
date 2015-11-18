package com.wds.jvm.basic;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wds on 2015/11/15.
 */
public class UnloadClass implements Opcodes {
    //private static final Logger LOGGER = LogManager.getLogger(UnloadClass.class);

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ClassWriter cw = new ClassWriter(jdk.internal.org.objectweb.asm.ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cw.visit(V1_7, ACC_PUBLIC, "Example", null, "java/lang/Object", null);
        MethodVisitor mw = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mw.visitVarInsn(ALOAD, 0);
        mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(0, 0);
        mw.visitEnd();

        mw = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([LJava/lang/String;)V", null, null);
        mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mw.visitLdcInsn("Hello World");
        mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");

        mw.visitInsn(RETURN);
        mw.visitMaxs(0, 0);
        mw.visitEnd();

        byte[] code = cw.toByteArray();

        for (int i = 0; i < 2; i++) {
            UnloadClassLoader unloadClass = new UnloadClassLoader();
            Method m = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);

            m.setAccessible(true);
            m.invoke(unloadClass, "Example", code, 0, code.length);
            m.setAccessible(false);

            unloadClass = null;
            //System.gc();
        }
    }
}
