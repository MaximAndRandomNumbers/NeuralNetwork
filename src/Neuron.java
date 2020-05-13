public class Neuron {
    public double input, output, mistake;

    public Neuron(){}
    public Neuron(double in){
        input = in;
    }
    public Neuron(double in, double out){
        input = in;
        output = out;
    }
    public static double func(double x) {
        return 1/(1+Math.exp(-x));
    }
    public void countAndSetOutput(){
        this.output = Neuron.func(this.input);
    }
}
