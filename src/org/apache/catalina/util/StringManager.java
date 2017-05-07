package org.apache.catalina.util;

/**
 * Created by clearbug on 2017/5/7.
 */

import java.text.MessageFormat;
import java.util.Hashtable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * An internationalization / localization helper class which reduces the bother of handing ResourceBundles and takes care of the common cases of message formating which otherwise require the creation of Object arrays and such.
 *
 * The StringManager operates on a package basis. One StringManager per package can be created and accessed via the getManager method call.
 *
 * The StringManager will look for a ResourceBundle named by the package name given plus the suffix of "LocalStrings". In practice, this means that the localized information will be contained in a LocalStrings.properties file located in the package directory of the classpath.
 *
 * Please see the documentation for java.util.ResourceBundle for more information.
 */
public class StringManager {

    /**
     * The ResourceBundle for this StringManager.
     */
    private ResourceBundle bundle;

    /**
     * Create a new StringManager for a given package. This is a private method and call access to it is arbitrated by the static getManager method call so that only one StringManager per package will be created.
     *
     * @param packageName Name of package to create StringManager for.
     */
    private StringManager(String packageName) {
        String bundleName = packageName + ".LocalStrings";
        bundle = ResourceBundle.getBundle(bundleName);
    }

    /**
     * Get a string from the underlyint resource bundle.
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        if (key == null) {
            String msg = "key is null";
            throw new NullPointerException(msg);
        }

        String str = null;

        try {
            str = bundle.getString(key);
        } catch (MissingResourceException mre) {
            str = "Cannot find message associated with key '" + key + "'";
        }

        return str;
    }

    /**
     * Get a string from the underlying resource bundle and format it with the given set of arguments.
     *
     * @param key
     * @param args
     * @return
     */
    public String getString(String key, Object[] args) {
        String isString = null;
        String value = getString(key);

        // this check for the runtime exception is some pre 1.1.6 VM's don't do an automatic toString() on the passed in objects and barf out
        try {
            // ensure the arguments are not null so pre 1.2 VM's don't barf
            Object nonNullArgs[] = args;
            for (int i = 0; i < args.length; i++) {
                if (args[i] == null) {
                    if (nonNullArgs == args)
                        nonNullArgs = (Object[]) args.clone();
                    nonNullArgs[i] = "null";
                }
            }

            isString = MessageFormat.format(value, nonNullArgs);
        } catch (IllegalArgumentException iae) {
            StringBuffer buf = new StringBuffer();
            buf.append(value);
            for (int i = 0; i < args.length; i++) {
                buf.append(" arg[" + i + "]=" + args[i]);
            }
            isString = buf.toString();
        }
        return isString;
    }

    /**
     * Get a string from the underlying resource bundle and format it with the given object argument.
     * This argument can of course be a String object.
     *
     * @param key
     * @param arg
     * @return
     */
    public String getString(String key, Object arg) {
        Object[] args = new Object[] {arg};
        return getString(key, args);
    }

    /**
     * Get a string from the underlying resource bundle and format it with the given object arguments.
     * These arguments can of course be String objects.
     *
     * @param key
     * @param arg1
     * @param arg2
     * @return
     */
    public String getString(String key, Object arg1, Object arg2) {
        Object[] args = new Object[] {arg1, arg2};
        return getString(key, args);
    }

    /**
     * Get a string from the underlying resource bundle and format it with the given object arguments.
     * These arguments can of course be String objects.
     *
     * @param key
     * @param arg1
     * @param arg2
     * @param arg3
     * @return
     */
    public String getString(String key, Object arg1, Object arg2, Object arg3) {
        Object[] args = new Object[] {arg1, arg2, arg3};
        return getString(key, args);
    }

    /**
     * Get a string from the underlying resource bundle and format it with the given object arguments.
     * These arguments can of course be String objects.
     *
     * @param key
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @return
     */
    public String getString(String key, Object arg1, Object arg2, Object arg3, Object arg4) {
        Object[] args = new Object[] {arg1, arg2, arg3, arg4};
        return getString(key, args);
    }

    // --------------------------------------------------------------
    // STATIC SUPPORT METHODS
    // --------------------------------------------------------------

    private static Hashtable managers = new Hashtable();

    /**
     * Get the StringManager for a particular package.
     * If a manager for a package already exists, it will be reused, else a new StringManager will be created and returned.
     *
     * @param packageName
     * @return
     */
    public synchronized static StringManager getManager(String packageName) {
        StringManager mgr = (StringManager) managers.get(packageName);
        if (mgr == null) {
            mgr = new StringManager(packageName);
            managers.put(packageName, mgr);
        }
        return mgr;
    }

}
