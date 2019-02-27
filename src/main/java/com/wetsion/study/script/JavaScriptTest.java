package com.wetsion.study.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @CLassName: JavaScriptTest
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2019/1/18 3:44 PM
 * @Version: 1.0
 */
public class JavaScriptTest {
    public static void main(String[] args) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('hello world')");
    }
}
