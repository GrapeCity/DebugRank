package com.grapecity.debugrank.javalib.entities;

/**
 * Created by chrisr on 1/27/2016.
 */
public class Result
{
    private String result;

    private String[] stdout;

    private String[] diff_status;

    private String hash;

    private String error_code;

    private String[] signal;

    private String[] stderr;

    private String codechecker_hash;

    private String custom_score;

    private String[] memory;

    private String[] message;

    private String[] time;

    private String[] censored_stderr;

    private String created_at;

    private String custom_status;

    private String server;

    private String callback_url;

    private String compilemessage;

    private String censored_compile_message;

    private String response_s3_path;

    public String getResult ()
    {
        return result;
    }

    public void setResult (String result)
    {
        this.result = result;
    }

    public String[] getStdout ()
    {
        return stdout;
    }

    public void setStdout (String[] stdout)
    {
        this.stdout = stdout;
    }

    public String[] getDiff_status ()
    {
        return diff_status;
    }

    public void setDiff_status (String[] diff_status)
    {
        this.diff_status = diff_status;
    }

    public String getHash ()
    {
        return hash;
    }

    public void setHash (String hash)
    {
        this.hash = hash;
    }

    public String getError_code ()
    {
        return error_code;
    }

    public void setError_code (String error_code)
    {
        this.error_code = error_code;
    }

    public String[] getSignal ()
    {
        return signal;
    }

    public void setSignal (String[] signal)
    {
        this.signal = signal;
    }

    public String[] getStderr ()
    {
        return stderr;
    }

    public void setStderr (String[] stderr)
    {
        this.stderr = stderr;
    }

    public String getCodechecker_hash ()
    {
        return codechecker_hash;
    }

    public void setCodechecker_hash (String codechecker_hash)
    {
        this.codechecker_hash = codechecker_hash;
    }

    public String getCustom_score ()
{
    return custom_score;
}

    public void setCustom_score (String custom_score)
    {
        this.custom_score = custom_score;
    }

    public String[] getMemory ()
    {
        return memory;
    }

    public void setMemory (String[] memory)
    {
        this.memory = memory;
    }

    public String[] getMessage ()
    {
        return message;
    }

    public void setMessage (String[] message)
    {
        this.message = message;
    }

    public String[] getTime ()
    {
        return time;
    }

    public void setTime (String[] time)
    {
        this.time = time;
    }

    public String[] getCensored_stderr ()
    {
        return censored_stderr;
    }

    public void setCensored_stderr (String[] censored_stderr)
    {
        this.censored_stderr = censored_stderr;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getCustom_status ()
{
    return custom_status;
}

    public void setCustom_status (String custom_status)
    {
        this.custom_status = custom_status;
    }

    public String getServer ()
    {
        return server;
    }

    public void setServer (String server)
    {
        this.server = server;
    }

    public String getCallback_url ()
    {
        return callback_url;
    }

    public void setCallback_url (String callback_url)
    {
        this.callback_url = callback_url;
    }

    public String getCompilemessage ()
    {
        return compilemessage;
    }

    public void setCompilemessage (String compilemessage)
    {
        this.compilemessage = compilemessage;
    }

    public String getCensored_compile_message ()
    {
        return censored_compile_message;
    }

    public void setCensored_compile_message (String censored_compile_message)
    {
        this.censored_compile_message = censored_compile_message;
    }

    public String getResponse_s3_path ()
    {
        return response_s3_path;
    }

    public void setResponse_s3_path (String response_s3_path)
    {
        this.response_s3_path = response_s3_path;
    }
}