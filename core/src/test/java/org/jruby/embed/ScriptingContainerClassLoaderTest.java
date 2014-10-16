package org.jruby.embed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Test;

public class ScriptingContainerClassLoaderTest {
    @Test
    public void testClassloader() throws Exception {
        // that is the classloader which loaded this class here and its dependent classes
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try {
            // make sure we have classloader which does not find jruby
            ClassLoader c = new URLClassLoader( new URL[] {}, null );
            try {
                c.loadClass( "org.jruby.embed.ScriptingContainer" );
                fail( "this classloader shall not find jruby" );
            }
            catch( ClassNotFoundException expected){}
            
            // set it as context classloader
            Thread.currentThread().setContextClassLoader( c );
            
            // we do have an instance of "jruby" loaded via some other classloader
            ScriptingContainer instance = new ScriptingContainer();
            String result = instance.runScriptlet( "$LOAD_PATH" ).toString();
            assertNotNull(result);
            
            assertEquals(ScriptingContainer.class.getClassLoader(), cl);
        }
        finally {
            Thread.currentThread().setContextClassLoader( cl );
        }
    }
}