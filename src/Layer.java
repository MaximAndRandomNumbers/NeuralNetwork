public class Layer {
    public Neuron[] neurons;
    public int numberofNeurons;
    public double[][] weights;
    public double[] biasWeights;

    public Layer(int numberOfNeurons){
        neurons = new Neuron[numberOfNeurons];
        this.numberofNeurons = numberOfNeurons;
        initializeNeurons();
    }

    private void initializeNeurons(){
        for(int i = 0; i < numberofNeurons; i++){
            neurons[i] = new Neuron();
        }
    }
    public void setInput(double[] inputs){
        if(this.numberofNeurons != inputs.length) {
            System.out.println("Количество входных нейронов не совпадает с количеством данных");
            return;
        }

        for (int i = 0; i < inputs.length; i++){
            this.neurons[i] = new Neuron(inputs[i], inputs[i]);
        }
    }
}
