/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author mota
 */
public class MidiFilter extends FileFilter {

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = ExtUtil.getExtension(f);
        if (extension != null) {
            return extension.equals(ExtUtil.mid);
        }

        return false;
    }

    public String getDescription() {
        return "Arquivos MIDI";
    }
    
}
