package com.cflint;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cflint.api.CFLintAPI;
import com.cflint.api.CFLintResult;
import com.cflint.config.ConfigBuilder;
import com.cflint.exception.CFLintScanException;

public class TestCFBugs_ArgsType {

    private CFLintAPI cfBugs;

    @Before
    public void setUp() throws Exception {
        final ConfigBuilder configBuilder = new ConfigBuilder().include("ARG_TYPE_MISSING", "ARG_TYPE_ANY");
        cfBugs = new CFLintAPI(configBuilder.build());
    }

    @Test
    public void testMissingType() throws CFLintScanException {
        final String cfcSrc = "<cfcomponent>\r\n" + "<cffunction name=\"test\" returnType=\"void\">\r\n"
                + "	<cfargument name=\"xyz\" default=\"123\">\r\n" + "</cffunction>\r\n" + "</cfcomponent>\r\n";
        CFLintResult lintresult = cfBugs.scan(cfcSrc, "test");
        final List<BugInfo> result = lintresult.getIssues().values().iterator().next();
        assertEquals(1, result.size());
        assertEquals("ARG_TYPE_MISSING", result.get(0).getMessageCode());
        assertEquals(3, result.get(0).getLine());
        assertEquals(Levels.WARNING, result.get(0).getSeverity());
        assertEquals("Argument xyz is missing a type.", result.get(0).getMessage());
    }

    @Test
    public void testMissingTypeNoTags() throws CFLintScanException {
        final String cfcSrc = "component {\r\n" + "public void function test(arg1) {\r\n" + "}\r\n" + "}\r\n";
        CFLintResult lintresult = cfBugs.scan(cfcSrc, "test");
        final List<BugInfo> result = lintresult.getIssues().values().iterator().next();
        assertEquals(1, result.size());
        assertEquals("ARG_TYPE_MISSING", result.get(0).getMessageCode());
        assertEquals(2, result.get(0).getLine());
        assertEquals(Levels.WARNING, result.get(0).getSeverity());
        assertEquals("Argument arg1 is missing a type.", result.get(0).getMessage());
    }

    @Test
    public void testTypeAny() throws CFLintScanException {
        final String cfcSrc = "<cfcomponent>\r\n" + "<cffunction name=\"test\" returnType=\"void\">\r\n"
                + "	<cfargument name=\"xyz\" default=\"123\" type=\"any\">\r\n" + "</cffunction>\r\n"
                + "</cfcomponent>\r\n";
        CFLintResult lintresult = cfBugs.scan(cfcSrc, "test");
        final List<BugInfo> result = lintresult.getIssues().values().iterator().next();
        assertEquals(1, result.size());
        assertEquals("ARG_TYPE_ANY", result.get(0).getMessageCode());
        assertEquals(3, result.get(0).getLine());
        assertEquals(Levels.WARNING, result.get(0).getSeverity());
        assertEquals("Argument xyz is any. Please change to be a more specific type.", result.get(0).getMessage());
    }

    @Test
    public void testTypeAnyNoTags() throws CFLintScanException {
        final String cfcSrc = "component {\r\n" + "public void function test(any arg1, numeric arg2) {\r\n" + "}\r\n"
                + "}\r\n";
        CFLintResult lintresult = cfBugs.scan(cfcSrc, "test");
        final List<BugInfo> result = lintresult.getIssues().values().iterator().next();
        assertEquals(1, result.size());
        assertEquals("ARG_TYPE_ANY", result.get(0).getMessageCode());
        assertEquals(2, result.get(0).getLine());
        assertEquals(Levels.WARNING, result.get(0).getSeverity());
        assertEquals("Argument arg1 is any. Please change to be a more specific type.", result.get(0).getMessage());
    }

}
