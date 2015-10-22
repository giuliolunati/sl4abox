FTPCMD := "cd sl4abox\n\
put bin/ScriptingLayerForAndroid.apk sl4abox.apk"

.PHONY: publish
publish:
	echo -e $(FTPCMD)|ftp -v giuliolunati.altervista.org

.PHONY: download
download:
	wget http://giuliolunati.altervista.org/sl4abox/sl4abox.apk
