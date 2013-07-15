/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.city.evolutions.input.instruction.phone;

import au.edu.newcastle.city.evolutions.input.instruction.BaseInstruction;

/**
 *
 * @author Ross Bille
 */
public class BasePhoneInstruction extends BaseInstruction
{
    private String id;
    private String OS;

    public BasePhoneInstruction() 
    {
        super();
        this.id = "NA";
        this.OS = "NA";
    }
    
}
