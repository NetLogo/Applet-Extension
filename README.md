# NetLogo Applet Extension

This extension allows you to access applet arguments from within NetLogo code, through the `get` primitive.

Currently, the only existing primitive is `get`, which is demonstrated as follows to retrieve some applet argument named "bippy":

```
applet:get "bippy"
```

## Building

Use the NETLOGO environment variable to tell the Makefile which NetLogoLite.jar to compile against.  For example:

    NETLOGO=/Applications/NetLogo\\\ 5.0
    make

If compilation succeeds, `applet.jar` will be created.

## Terms of Use

[![CC0](http://i.creativecommons.org/p/zero/1.0/88x31.png)](http://creativecommons.org/publicdomain/zero/1.0/)

The NetLogo Applet extension is in the public domain.  To the extent possible under law, Uri Wilensky has waived all copyright and related or neighboring rights.
