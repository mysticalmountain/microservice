script_dir=`dirname $0`
APP_HOME=`cd "$script_dir"; pwd`
echo "application home: $APP_HOME"
cd $APP_HOME

PIDFILE=$APP_HOME/.pid
CLASSPATH=$APP_HOME/libs/permission-1.0.jar

JAVA_OPTS="-server -Xmx512M -Xms512M -XX:MaxPermSize=256M -XX:PermSize=256M -Xss256k
            -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSParallelRemarkEnabled
            -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=0
            -XX:+CMSClassUnloadingEnabled -XX:LargePageSizeInBytes=128M
            -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly
            -XX:+HeapDumpOnOutOfMemoryError
            -XX:+PrintGCDetails
            -XX:+PrintGCDateStamps
            -Xloggc:$APP_HOME/logs/gc.log
            -Duser.timezone=Asia/Shanghai -Dfile.encoding=UTF-8 -Dlocal=false"
pid=-1

start() {
    pid=`ps -ef | grep $APP_HOME | grep -v grep | awk '{print $2}'`
    if [ -z $pid ]; then
        java -cp $CLASSPATH $JAVA_OPTS com.andx.micro.permission.PermissionApp   >> config.log 2>&1 &
        echo $!>$PIDFILE
        echo "running pid: $!"
    else
        echo "server is runing pid:$pid"
    fi
}

stop() {
    pid=`ps -ef | grep $APP_HOME | grep -v grep | awk '{print $2}'`
    if [ ! -z $pid ]; then
        echo "server is still runing pid:$pid"
        kill -9 $pid
    fi
    echo "server stoped!"
    rm -f $PIDFILE
}

net() {
    netstat -anp | grep `cat $PIDFILE`
}

log() {
    tail -fn 300 $APP_HOME/logs/clearing.log
}

lsof() {
    /usr/sbin/lsof -p `cat $PIDFILE`
}

heap() {
    dd=`date +%m%d-%H%M`
    mkdir -p $APP_HOME/logs/heap
    jmap -histo `cat $PIDFILE` > $APP_HOME/logs/heap/$dd.txt
    jmap -dump:format=b,file=$APP_HOME/logs/heap/$dd.bin `cat $PIDFILE`
}

gc() {
    jstat -gc `cat $PIDFILE` 5000
}


case "$1" in
    net)
        net;;
    log)
        log;;
    gc)
        gc;;
    lsof)
        lsof;;
    heap)
        heap;;
    start)
        start;;
    stop)
        stop;;
    restart)
        stop
        start;;
    *)
        echo "Usage: ./server {start|stop|restart|net|log|lsof|heap|gc}"
        exit;
esac
exit 0;
