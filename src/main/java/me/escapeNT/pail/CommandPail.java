/*
 * Pore
 * Copyright (c) 2014-2015, Lapis <https://github.com/LapisBlue>
 *
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package me.escapeNT.pail;

import java.util.Collections;
import java.util.List;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.source.ConsoleSource;

import com.google.common.base.Optional;

public class CommandPail implements CommandCallable {

    private Text desc = Texts.of("Opens the main Pail window.");
    private Text help = Texts.of("Opens the main Pail window.");
    private Pail pail;
    
    public CommandPail(Pail pail) {
        this.pail = pail;
    }

    public Optional<CommandResult> process(CommandSource source, String arguments) throws CommandException {
        if (arguments == "" && source instanceof ConsoleSource) {
            pail.getMainWindow().setVisible(true); 
            pail.getMainWindow().requestFocus(); 
        }
        return Optional.of(CommandResult.success());
    }

    public List<String> getSuggestions(CommandSource source, String arguments) throws CommandException {
        return Collections.emptyList();
    }

    public boolean testPermission(CommandSource source) {
        return true;
    }

    public Optional<Text> getShortDescription(CommandSource source) {
        return Optional.of(desc);
    }

    public Optional<Text> getHelp(CommandSource source) {
        return Optional.of(help);
    }

    public Text getUsage(CommandSource source) {
        return Texts.of("/<command>");
    }

}
