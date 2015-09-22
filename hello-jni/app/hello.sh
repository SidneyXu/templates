#!/bin/sh
export ProjectPath=$(cd "."; pwd)
#echo $ProjectPath

export TargetClassName="com.example.jni.Hello"
export SourceFile="${ProjectPath}/src/main/java"
export TargetPath="${ProjectPath}/src/main/jni"

#echo $SourceFile
cd "${SourceFile}"
javah -d ${TargetPath} -classpath "${SourceFile}" "${TargetClassName}"
echo -d ${TargetPath} -