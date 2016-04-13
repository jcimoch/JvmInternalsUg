package pl.jcimoch.ug;

import java.lang.instrument.Instrumentation;

/**
 * Created by Jaroslaw on 13.04.2016.
 */
public class Agent007 {
    public static void premain(String agentArgs, Instrumentation inst) {
        // Transformer registration
        //inst.addTransformer(new MyClassFileTransformer());
        //inst.addTransformer(new JavassistSimpleTransformer());
        inst.addTransformer(new JavassistSimpleTransformer());
    }

}
