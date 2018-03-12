#include <jni.h>

JNIEXPORT jstring JNICALL
Java_mostpopular_kapil_com_nycmostpopular_ui_allarticles_AllArticlesAsyncResource_getNYCApiKey(JNIEnv *env, jobject instance) {

 return (*env)->  NewStringUTF(env, "75bea8d636594b15a2664037f0e73c8c");
}

