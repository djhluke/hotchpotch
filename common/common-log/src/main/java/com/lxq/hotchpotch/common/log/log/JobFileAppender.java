package com.lxq.hotchpotch.common.log.log;

import com.lxq.hotchpotch.common.log.pojo.LogResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * store trigger log in each log-file
 *
 * @author luxinqiang.
 */
public class JobFileAppender {

    // for JobThread (support log for child thread of job handler)
    // public static ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static final InheritableThreadLocal<String> contextHolder = new InheritableThreadLocal<String>();
    private static final Logger logger = LoggerFactory.getLogger(JobFileAppender.class);
    /**
     * log base path
     * <p>
     * strut like:
     * ---/
     * ---/gluesource/
     * ---/gluesource/10_1514171108000.js
     * ---/gluesource/10_1514171108000.js
     * ---/2017-12-25/
     * ---/2017-12-25/639.log
     * ---/2017-12-25/821.log
     */
    //private static String logBasePath = "/data/applogs/xxl-job/jobhandler";
    private static String logBasePath = System.getProperty("user.dir") + "/log/jobhandler";
    private static String logPaths = System.getProperty("user.dir") + "/log";
    private static String glueSrcPath = logBasePath.concat("/gluesource");

    public static void initLogPath(String logPath) {
        // init
        if (logPath != null && logPath.trim().length() > 0) {
            logBasePath = logPath;
        }
        // mk base dir
        File logPathDir = new File(logBasePath);
        if (!logPathDir.exists()) {
            logPathDir.mkdirs();
        }
        logBasePath = logPathDir.getPath();

        // mk glue dir
        File glueBaseDir = new File(logPathDir, "gluesource");
        if (!glueBaseDir.exists()) {
            glueBaseDir.mkdirs();
        }
        glueSrcPath = glueBaseDir.getPath();

        if (logPath != null && logPath.trim().length() > 0) {
            logPaths = logPath;
        }
        // mk base dir
        File logPathDirs = new File(logPaths);
        if (!logPathDirs.exists()) {
            logPathDirs.mkdirs();
        }
        logPaths = logPathDirs.getPath();
    }

    public static String getLogPath() {
        return logBasePath;
    }

    public static String getGlueSrcPath() {
        return glueSrcPath;
    }

    public static String getLogPaths() {
        return logPaths;
    }

    /**
     * log filename, like "logPath/yyyy-MM-dd/9999.log"
     *
     * @param triggerDate
     * @param logId
     * @return
     */
    public static String makeLogFileName(Date triggerDate, String logId) {

        // filePath/yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    // avoid concurrent problem, can not be static
        File logFilePath = new File(getLogPath(), sdf.format(triggerDate));
        if (!logFilePath.exists()) {
            logFilePath.mkdir();
        }

        // filePath/yyyy-MM-dd/9999.log
        String logFileName = logFilePath.getPath()
                .concat(File.separator)
                .concat(logId)
                .concat(".log");
        return logFileName;
    }

    /**
     * append log
     *
     * @param logFileName
     * @param appendLog
     */
    public static void appendLog(String logFileName, String appendLog) {

        // log file
        if (logFileName == null || logFileName.trim().length() == 0) {
            return;
        }
        File logFile = new File(logFileName);

        if (!logFile.exists()) {
            try {
                File logParentFile = logFile.getParentFile();
                if (!logParentFile.exists()) {
                    logParentFile.mkdirs();
                }
                logFile.createNewFile();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return;
            }
        }

        // log
        if (appendLog == null) {
            appendLog = "";
        }
        appendLog += "\r\n";

        // append file content
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(logFile, true);
            fos.write(appendLog.getBytes(StandardCharsets.UTF_8));
            fos.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

    }

    /**
     * support read log-file
     *
     * @param logFileName
     * @return log content
     */
    public static LogResult readLog(String logFileName, int fromLineNum) {

        // valid log file
        if (logFileName == null || logFileName.trim().length() == 0) {
            return new LogResult(fromLineNum, 0, "readLog fail, logFile not found", true);
        }
        File logFile = new File(logFileName);

        if (!logFile.exists()) {
            return new LogResult(fromLineNum, 0, "readLog fail, logFile not exists", true);
        }

        // read file
        StringBuffer logContentBuffer = new StringBuffer();
        int toLineNum = 0;
        LineNumberReader reader = null;
        try {
            //reader = new LineNumberReader(new FileReader(logFile));
            reader = new LineNumberReader(new InputStreamReader(new FileInputStream(logFile), StandardCharsets.UTF_8));
            String line = null;

            while ((line = reader.readLine()) != null) {
                toLineNum = reader.getLineNumber();        // [from, to], start as 1
                if (toLineNum >= fromLineNum) {
                    logContentBuffer.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        // result
        LogResult logResult = new LogResult(fromLineNum, toLineNum, logContentBuffer.toString(), false);
        return logResult;

		/*
        // it will return the number of characters actually skipped
        reader.skip(Long.MAX_VALUE);
        int maxLineNum = reader.getLineNumber();
        maxLineNum++;	// 最大行号
        */
    }

    /**
     * read log data
     *
     * @param logFile
     * @return log line content
     */
    public static String readLines(File logFile) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(logFile), StandardCharsets.UTF_8));
            if (reader != null) {
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                return sb.toString();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    /**
     * 用于预警，查证模型执行的操作日志
     *
     * @param triggerDate
     * @param logId
     * @param type        输入类型
     * @return
     */
    public static String makeLog(Date triggerDate, String logId, String type) {

        // filePath/yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        File logFilePath = new File(getLogPaths().concat(File.separator).concat(type), sdf.format(triggerDate));
        if (!logFilePath.exists()) {
            logFilePath.mkdir();
        }

        // filePath/yyyy-MM-dd/9999.log
        String logFileName = logFilePath.getPath()
                .concat(File.separator)
                .concat(logId)
                .concat(".log");
        return logFileName;
    }
}
