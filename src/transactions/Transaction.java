/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactions;

import java.io.Serializable;

/**
 *
 * @author jodus
 */
public interface Transaction extends Serializable {
    public void execute();
}
