#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_xyc_jnidemo_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_xyc_jnidemo_MainActivity_getHelloJni(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("hello world");
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_xyc_jnidemo_MainActivity_stringFromJNI1(JNIEnv *env, jobject instance, jstring str_,
                                                 jint i) {
    const char *str = env->GetStringUTFChars(str_, 0);

    // TODO

    env->ReleaseStringUTFChars(str_, str);

    return env->NewStringUTF("dasdas");
}