/*
 * Copyright (C) 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.googlecode.android_scripting.interpreter;

import com.googlecode.android_scripting.interpreter.Interpreter;
import com.googlecode.android_scripting.language.ShellLanguage;
import com.googlecode.android_scripting.Log;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Damon Kohler (damonkohler@gmail.com)
 */
public class KboxInterpreter extends Interpreter {

  public KboxInterpreter(
      String name,
      String binary,
      String interactiveCommand,
      String scriptCommand,
      String extension,
      String chroot,
      String home
    ) {
    setExtension(extension);
    setName(name.replaceAll(" ","_"));
    setNiceName(name);
    setBinary(new File(binary));
    setInteractiveCommand(interactiveCommand);
    setScriptCommand(scriptCommand);
    setLanguage(new ShellLanguage());
    setHasInteractiveMode(true);
    Map<String, String> environment= new HashMap<String, String>();
    environment.put("CWD",chroot);
    environment.put("HOME",home);
    environment.put("TERM","linux");
    environment.put("TERMINFO","/usr/share/terminfo");
    environment.put("TERM","linux");
    if (! chroot.equals("/")) {
      environment.put("KBOX",chroot);
      environment.put("LD_LIBRARY_PATH",chroot+"/usr/lib:"+chroot+"/lib");
      environment.put("LD_PRELOAD",chroot+"/lib/libfakechroot.so");
      environment.put("FAKECHROOT_BASE",chroot);
      environment.put("FAKECHROOT_EXCLUDE_PATH","/data");
    }
    putAllEnvironmentVariables(environment);
  }

  public boolean hasInterpreterArchive() {
    return false;
  }

  public boolean hasExtrasArchive() {
    return false;
  }

  public boolean hasScriptsArchive() {
    return false;
  }

  public int getVersion() {
    return 0;
  }

  @Override
  public boolean isUninstallable() {
    return false;
  }

  @Override
  public boolean isInstalled() {
    return true;
  }
}
