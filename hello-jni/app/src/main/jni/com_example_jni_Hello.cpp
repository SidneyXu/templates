#include "com_example_jni_Hello.h"
/*
 * Class:     com_example_jni_Hello
 * Method:    hellojni
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_jni_Hello_hellojni
        (JNIEnv *env, jobject) {
    return env->NewStringUTF("hello from jni");
}