This is version 1.1.2 of Charva.
===============================

For instructions on building and installing this software, look at the
file "docs/Download.html".

The file "xterm.ti" in this directory contains a terminfo description
for the KDE terminal emulator on Linux (known as "konsole"). I have found 
that the standard "xterm" terminfo file distributed with Red Hat Linux
gives erratic results when used with the Charva package. The "xterm.ti"
file works reliably with Charva.

To compile the terminfo description, run the command "tic xterm.ti" 
as the root user. You may want to save the original version (on Red Hat,
this is /usr/share/terminfo/x/xterm) before doing this.

If you are using the GNOME desktop environment instead of KDE, don't even 
try to use the "gnome-terminal" emulator (the standard emulator for GNOME).
It is incompatible with the "xterm" terminfo file. Apparently this is a
known bug in gnome-terminal.

One of the most enjoyable things about developing open-source software
is getting feedback from people who have used it and found it useful.
If you find this software useful, or have suggestions about how it can
be improved, please send me a picture postcard from your home country
and let me know. My snail-mail address is:

    Rob Pitman
    8 Pickwood Road
    Irene
    0062 Centurion
    South Africa

Enjoy!
