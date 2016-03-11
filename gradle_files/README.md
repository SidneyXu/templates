# gradle_files

## Category

- android_app.gradle
	- Android application gradle file template.
- android_library.gradle
	- Android library gradle file template.
- android_archive.gradle
	- Android Archive
- android_eclipse.gradle
	- Android Application for Eclipse
- java.gradle
- javadoc_jar.gradle
- gradle.properties
- maven_publish.gradle
	- Publish archives to maven central.
- local_publish.gradle
	- Publish archives to location directory.


## Usage

### maven_publish.gradle

中文说明可以参考博客 [如何上传 Library 到 Maven 仓库](http://git.bookislife.com/post/2015/how-to-upload-library-to-maven-central/)

Steps

1. Put `maven_publish.gradle` file into your project root path.
2. Includes the following sentences in your app `build.gradle` file.
	```groovy
	apply from: '../maven_publish.gradle'
	```
3. Put `gradle.properties` into your project root path.
4. Modify the `gradle.properties`.
5. Publish the project.

    publish to nexus.
    ```bash
    gradle -q upload
	```
    publish to maven.
    ```bash
    gradle -q -Penv=pro upload
	```
    publish to local file.
    ```bash
    gradle -q -Penv=local upload
	```