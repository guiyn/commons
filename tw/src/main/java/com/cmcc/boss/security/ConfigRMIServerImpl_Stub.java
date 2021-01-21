package com.cmcc.boss.security;
// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

import java.io.*;
import java.lang.reflect.Method;
import java.rmi.*;
import java.rmi.server.*;

public final class ConfigRMIServerImpl_Stub extends RemoteStub
    implements ConfigRMIServer, Remote
{

    private static final Operation operations[] = {
        new Operation("java.lang.String getGenerateKey(java.lang.String)"), new Operation("int getSetKey(java.lang.String)"), new Operation("java.lang.String getcert(java.lang.String)"), new Operation("java.lang.String getlogfilepath()"), new Operation("java.lang.String getmainkey()"), new Operation("java.lang.String getnewkey(java.lang.String)"), new Operation("java.lang.String getoldkey(java.lang.String)"), new Operation("java.lang.String getprivatekey()"), new Operation("int getpv()"), new Operation("java.lang.String getrootcert()"), 
        new Operation("java.lang.String getselfcert()"), new Operation("long getvalidtime(java.lang.String)"), new Operation("void initiate()"), new Operation("void refreshWait(java.lang.String, java.lang.String, boolean)"), new Operation("int rollbackkey(java.lang.String)"), new Operation("void setpv(int)")
    };
    private static final long interfaceHash = 0x62982a113211d590L;
    private static final long serialVersionUID = 2L;
    private static boolean useNewInvoke;
    private static Method _fld$method_getGenerateKey_0;
    private static Method _fld$method_getSetKey_1;
    private static Method _fld$method_getcert_2;
    private static Method _fld$method_getlogfilepath_3;
    private static Method _fld$method_getmainkey_4;
    private static Method _fld$method_getnewkey_5;
    private static Method _fld$method_getoldkey_6;
    private static Method _fld$method_getprivatekey_7;
    private static Method _fld$method_getpv_8;
    private static Method _fld$method_getrootcert_9;
    private static Method _fld$method_getselfcert_10;
    private static Method _fld$method_getvalidtime_11;
    private static Method _fld$method_initiate_12;
    private static Method _fld$method_refreshWait_13;
    private static Method _fld$method_rollbackkey_14;
    private static Method _fld$method_setpv_15;
    static Class class$java$rmi$server$RemoteRef; /* synthetic field */
    static Class class$java$rmi$Remote; /* synthetic field */
    static Class class$java$lang$reflect$Method; /* synthetic field */
    static Class array$Ljava$lang$Object; /* synthetic field */
    static Class class$ConfigRMIServer; /* synthetic field */
    static Class class$java$lang$String; /* synthetic field */

    public ConfigRMIServerImpl_Stub()
    {
    }

    public ConfigRMIServerImpl_Stub(RemoteRef remoteref)
    {
        super(remoteref);
    }

    static Class class$(String s)
    {
        try
        {
            return Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
    }

    public String getGenerateKey(String s)
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                Object obj = ref.invoke(this, _fld$method_getGenerateKey_0, new Object[] {
                    s
                }, 0xc824d7a4df696c46L);
                return (String)obj;
            }
            RemoteCall remotecall = ref.newCall(this, operations, 0, 0x62982a113211d590L);
            try
            {
                ObjectOutput objectoutput = remotecall.getOutputStream();
                objectoutput.writeObject(s);
            }
            catch(IOException ioexception)
            {
                throw new MarshalException("error marshalling arguments", ioexception);
            }
            ref.invoke(remotecall);
            String s1;
            try
            {
                ObjectInput objectinput = remotecall.getInputStream();
                s1 = (String)objectinput.readObject();
            }
            catch(IOException ioexception1)
            {
                throw new UnmarshalException("error unmarshalling return", ioexception1);
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new UnmarshalException("error unmarshalling return", classnotfoundexception);
            }
            finally
            {
                ref.done(remotecall);
            }
            return s1;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public int getSetKey(String s)
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                Object obj = ref.invoke(this, _fld$method_getSetKey_1, new Object[] {
                    s
                }, 0x690a8ddbfcd6801fL);
                return ((Integer)obj).intValue();
            }
            RemoteCall remotecall = ref.newCall(this, operations, 1, 0x62982a113211d590L);
            try
            {
                ObjectOutput objectoutput = remotecall.getOutputStream();
                objectoutput.writeObject(s);
            }
            catch(IOException ioexception)
            {
                throw new MarshalException("error marshalling arguments", ioexception);
            }
            ref.invoke(remotecall);
            int i;
            try
            {
                ObjectInput objectinput = remotecall.getInputStream();
                i = objectinput.readInt();
            }
            catch(IOException ioexception1)
            {
                throw new UnmarshalException("error unmarshalling return", ioexception1);
            }
            finally
            {
                ref.done(remotecall);
            }
            return i;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public String getcert(String s)
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                Object obj = ref.invoke(this, _fld$method_getcert_2, new Object[] {
                    s
                }, 0x327929fb71076b7bL);
                return (String)obj;
            }
            RemoteCall remotecall = ref.newCall(this, operations, 2, 0x62982a113211d590L);
            try
            {
                ObjectOutput objectoutput = remotecall.getOutputStream();
                objectoutput.writeObject(s);
            }
            catch(IOException ioexception)
            {
                throw new MarshalException("error marshalling arguments", ioexception);
            }
            ref.invoke(remotecall);
            String s1;
            try
            {
                ObjectInput objectinput = remotecall.getInputStream();
                s1 = (String)objectinput.readObject();
            }
            catch(IOException ioexception1)
            {
                throw new UnmarshalException("error unmarshalling return", ioexception1);
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new UnmarshalException("error unmarshalling return", classnotfoundexception);
            }
            finally
            {
                ref.done(remotecall);
            }
            return s1;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public String getlogfilepath()
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                Object obj = ref.invoke(this, _fld$method_getlogfilepath_3, null, 0x550711097b63bb27L);
                return (String)obj;
            }
            RemoteCall remotecall = ref.newCall(this, operations, 3, 0x62982a113211d590L);
            ref.invoke(remotecall);
            String s;
            try
            {
                ObjectInput objectinput = remotecall.getInputStream();
                s = (String)objectinput.readObject();
            }
            catch(IOException ioexception)
            {
                throw new UnmarshalException("error unmarshalling return", ioexception);
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new UnmarshalException("error unmarshalling return", classnotfoundexception);
            }
            finally
            {
                ref.done(remotecall);
            }
            return s;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public String getmainkey()
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                Object obj = ref.invoke(this, _fld$method_getmainkey_4, null, 0x7a7a70ee055787ceL);
                return (String)obj;
            }
            RemoteCall remotecall = ref.newCall(this, operations, 4, 0x62982a113211d590L);
            ref.invoke(remotecall);
            String s;
            try
            {
                ObjectInput objectinput = remotecall.getInputStream();
                s = (String)objectinput.readObject();
            }
            catch(IOException ioexception)
            {
                throw new UnmarshalException("error unmarshalling return", ioexception);
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new UnmarshalException("error unmarshalling return", classnotfoundexception);
            }
            finally
            {
                ref.done(remotecall);
            }
            return s;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public String getnewkey(String s)
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                Object obj = ref.invoke(this, _fld$method_getnewkey_5, new Object[] {
                    s
                }, 0x796678a58fd7e1eL);
                return (String)obj;
            }
            RemoteCall remotecall = ref.newCall(this, operations, 5, 0x62982a113211d590L);
            try
            {
                ObjectOutput objectoutput = remotecall.getOutputStream();
                objectoutput.writeObject(s);
            }
            catch(IOException ioexception)
            {
                throw new MarshalException("error marshalling arguments", ioexception);
            }
            ref.invoke(remotecall);
            String s1;
            try
            {
                ObjectInput objectinput = remotecall.getInputStream();
                s1 = (String)objectinput.readObject();
            }
            catch(IOException ioexception1)
            {
                throw new UnmarshalException("error unmarshalling return", ioexception1);
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new UnmarshalException("error unmarshalling return", classnotfoundexception);
            }
            finally
            {
                ref.done(remotecall);
            }
            return s1;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public String getoldkey(String s)
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                Object obj = ref.invoke(this, _fld$method_getoldkey_6, new Object[] {
                    s
                }, 0xfdf9284c6079f875L);
                return (String)obj;
            }
            RemoteCall remotecall = ref.newCall(this, operations, 6, 0x62982a113211d590L);
            try
            {
                ObjectOutput objectoutput = remotecall.getOutputStream();
                objectoutput.writeObject(s);
            }
            catch(IOException ioexception)
            {
                throw new MarshalException("error marshalling arguments", ioexception);
            }
            ref.invoke(remotecall);
            String s1;
            try
            {
                ObjectInput objectinput = remotecall.getInputStream();
                s1 = (String)objectinput.readObject();
            }
            catch(IOException ioexception1)
            {
                throw new UnmarshalException("error unmarshalling return", ioexception1);
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new UnmarshalException("error unmarshalling return", classnotfoundexception);
            }
            finally
            {
                ref.done(remotecall);
            }
            return s1;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public String getprivatekey()
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                Object obj = ref.invoke(this, _fld$method_getprivatekey_7, null, 0x1a837284f5dff920L);
                return (String)obj;
            }
            RemoteCall remotecall = ref.newCall(this, operations, 7, 0x62982a113211d590L);
            ref.invoke(remotecall);
            String s;
            try
            {
                ObjectInput objectinput = remotecall.getInputStream();
                s = (String)objectinput.readObject();
            }
            catch(IOException ioexception)
            {
                throw new UnmarshalException("error unmarshalling return", ioexception);
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new UnmarshalException("error unmarshalling return", classnotfoundexception);
            }
            finally
            {
                ref.done(remotecall);
            }
            return s;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public int getpv()
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                Object obj = ref.invoke(this, _fld$method_getpv_8, null, 0x25b8371ed9942c7dL);
                return ((Integer)obj).intValue();
            }
            RemoteCall remotecall = ref.newCall(this, operations, 8, 0x62982a113211d590L);
            ref.invoke(remotecall);
            int i;
            try
            {
                ObjectInput objectinput = remotecall.getInputStream();
                i = objectinput.readInt();
            }
            catch(IOException ioexception)
            {
                throw new UnmarshalException("error unmarshalling return", ioexception);
            }
            finally
            {
                ref.done(remotecall);
            }
            return i;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public String getrootcert()
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                Object obj = ref.invoke(this, _fld$method_getrootcert_9, null, 0x3bf7f3bf7ec46b02L);
                return (String)obj;
            }
            RemoteCall remotecall = ref.newCall(this, operations, 9, 0x62982a113211d590L);
            ref.invoke(remotecall);
            String s;
            try
            {
                ObjectInput objectinput = remotecall.getInputStream();
                s = (String)objectinput.readObject();
            }
            catch(IOException ioexception)
            {
                throw new UnmarshalException("error unmarshalling return", ioexception);
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new UnmarshalException("error unmarshalling return", classnotfoundexception);
            }
            finally
            {
                ref.done(remotecall);
            }
            return s;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public String getselfcert()
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                Object obj = ref.invoke(this, _fld$method_getselfcert_10, null, 0xfeb8d78c05ec81bbL);
                return (String)obj;
            }
            RemoteCall remotecall = ref.newCall(this, operations, 10, 0x62982a113211d590L);
            ref.invoke(remotecall);
            String s;
            try
            {
                ObjectInput objectinput = remotecall.getInputStream();
                s = (String)objectinput.readObject();
            }
            catch(IOException ioexception)
            {
                throw new UnmarshalException("error unmarshalling return", ioexception);
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new UnmarshalException("error unmarshalling return", classnotfoundexception);
            }
            finally
            {
                ref.done(remotecall);
            }
            return s;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public long getvalidtime(String s)
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                Object obj = ref.invoke(this, _fld$method_getvalidtime_11, new Object[] {
                    s
                }, 0x1c6eb6c29f78348dL);
                return ((Long)obj).longValue();
            }
            RemoteCall remotecall = ref.newCall(this, operations, 11, 0x62982a113211d590L);
            try
            {
                ObjectOutput objectoutput = remotecall.getOutputStream();
                objectoutput.writeObject(s);
            }
            catch(IOException ioexception)
            {
                throw new MarshalException("error marshalling arguments", ioexception);
            }
            ref.invoke(remotecall);
            long l;
            try
            {
                ObjectInput objectinput = remotecall.getInputStream();
                l = objectinput.readLong();
            }
            catch(IOException ioexception1)
            {
                throw new UnmarshalException("error unmarshalling return", ioexception1);
            }
            finally
            {
                ref.done(remotecall);
            }
            return l;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public void initiate()
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                ref.invoke(this, _fld$method_initiate_12, null, 0xe42d9a6937524756L);
            } else
            {
                RemoteCall remotecall = ref.newCall(this, operations, 12, 0x62982a113211d590L);
                ref.invoke(remotecall);
                ref.done(remotecall);
            }
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public void refreshWait(String s, String s1, boolean flag)
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                ref.invoke(this, _fld$method_refreshWait_13, new Object[] {
                    s, s1, new Boolean(flag)
                }, 0x53c3ac3365a0cf90L);
            } else
            {
                RemoteCall remotecall = ref.newCall(this, operations, 13, 0x62982a113211d590L);
                try
                {
                    ObjectOutput objectoutput = remotecall.getOutputStream();
                    objectoutput.writeObject(s);
                    objectoutput.writeObject(s1);
                    objectoutput.writeBoolean(flag);
                }
                catch(IOException ioexception)
                {
                    throw new MarshalException("error marshalling arguments", ioexception);
                }
                ref.invoke(remotecall);
                ref.done(remotecall);
            }
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public int rollbackkey(String s)
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                Object obj = ref.invoke(this, _fld$method_rollbackkey_14, new Object[] {
                    s
                }, 0xbb62a99d77488ee4L);
                return ((Integer)obj).intValue();
            }
            RemoteCall remotecall = ref.newCall(this, operations, 14, 0x62982a113211d590L);
            try
            {
                ObjectOutput objectoutput = remotecall.getOutputStream();
                objectoutput.writeObject(s);
            }
            catch(IOException ioexception)
            {
                throw new MarshalException("error marshalling arguments", ioexception);
            }
            ref.invoke(remotecall);
            int i;
            try
            {
                ObjectInput objectinput = remotecall.getInputStream();
                i = objectinput.readInt();
            }
            catch(IOException ioexception1)
            {
                throw new UnmarshalException("error unmarshalling return", ioexception1);
            }
            finally
            {
                ref.done(remotecall);
            }
            return i;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    public void setpv(int i)
        throws RemoteException
    {
        try
        {
            if(useNewInvoke)
            {
                ref.invoke(this, _fld$method_setpv_15, new Object[] {
                    new Integer(i)
                }, 0x2aa53fa13d48c2cdL);
            } else
            {
                RemoteCall remotecall = ref.newCall(this, operations, 15, 0x62982a113211d590L);
                try
                {
                    ObjectOutput objectoutput = remotecall.getOutputStream();
                    objectoutput.writeInt(i);
                }
                catch(IOException ioexception)
                {
                    throw new MarshalException("error marshalling arguments", ioexception);
                }
                ref.invoke(remotecall);
                ref.done(remotecall);
            }
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    static 
    {
        try
        {
            (class$java$rmi$server$RemoteRef == null ? (class$java$rmi$server$RemoteRef = class$("java.rmi.server.RemoteRef")) : class$java$rmi$server$RemoteRef).getMethod("invoke", new Class[] {
                class$java$rmi$Remote == null ? (class$java$rmi$Remote = class$("java.rmi.Remote")) : class$java$rmi$Remote, class$java$lang$reflect$Method == null ? (class$java$lang$reflect$Method = class$("java.lang.reflect.Method")) : class$java$lang$reflect$Method, array$Ljava$lang$Object == null ? (array$Ljava$lang$Object = class$("[Ljava.lang.Object;")) : array$Ljava$lang$Object, Long.TYPE
            });
            useNewInvoke = true;
            _fld$method_getGenerateKey_0 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("getGenerateKey", new Class[] {
                class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String
            });
            _fld$method_getSetKey_1 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("getSetKey", new Class[] {
                class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String
            });
            _fld$method_getcert_2 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("getcert", new Class[] {
                class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String
            });
            _fld$method_getlogfilepath_3 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("getlogfilepath", new Class[0]);
            _fld$method_getmainkey_4 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("getmainkey", new Class[0]);
            _fld$method_getnewkey_5 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("getnewkey", new Class[] {
                class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String
            });
            _fld$method_getoldkey_6 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("getoldkey", new Class[] {
                class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String
            });
            _fld$method_getprivatekey_7 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("getprivatekey", new Class[0]);
            _fld$method_getpv_8 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("getpv", new Class[0]);
            _fld$method_getrootcert_9 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("getrootcert", new Class[0]);
            _fld$method_getselfcert_10 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("getselfcert", new Class[0]);
            _fld$method_getvalidtime_11 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("getvalidtime", new Class[] {
                class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String
            });
            _fld$method_initiate_12 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("initiate", new Class[0]);
            _fld$method_refreshWait_13 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("refreshWait", new Class[] {
                class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String, class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String, Boolean.TYPE
            });
            _fld$method_rollbackkey_14 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("rollbackkey", new Class[] {
                class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String
            });
            _fld$method_setpv_15 = (class$ConfigRMIServer == null ? (class$ConfigRMIServer = class$("com.cmcc.boss.security.ConfigRMIServer")) : class$ConfigRMIServer).getMethod("setpv", new Class[] {
                Integer.TYPE
            });
        }
        catch(NoSuchMethodException _ex)
        {
            useNewInvoke = false;
        }
    }
}
