# NetLogo Applet Extension

This extension allows you to access applet arguments from within NetLogo code, through the `get` primitive.

Currently, the only existing primitive is `get`, which is demonstrated as follows to retrieve some applet argument named "bippy":

```
applet:get "bippy"
```

Note that using applets at all is deprecated; [details here](https://github.com/NetLogo/NetLogo/wiki/Applets).

## Building

To build, make sure you have [sbt](http://www.scala-sbt.org) installed, then run:

    sbt package

If compilation succeeds, `applet.zip` will be created.

## Terms of Use

[![CC0](http://i.creativecommons.org/p/zero/1.0/88x31.png)](http://creativecommons.org/publicdomain/zero/1.0/)

The NetLogo Applet extension is in the public domain.  To the extent possible under law, Uri Wilensky has waived all copyright and related or neighboring rights.
