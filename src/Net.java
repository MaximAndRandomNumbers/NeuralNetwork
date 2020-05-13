import jdk.nashorn.api.tree.NewTree;

import java.awt.*;

public class Net {
    private Layer[] layers;
    private final double coef = 0.5;

    public Net(int[] sizes) {
        setLayers(sizes);
        setWeights();
    }

    public double[] getResult(double[] inputs) {
        setInput(inputs);
        feedForward();
        //displayOutput();
        double[] result = new double[layers[layers.length - 1].numberofNeurons];
        for (int i = 0; i < result.length; i++) {
            result[i] = layers[layers.length - 1].neurons[i].output;
        }
        return result;
    }

    private void displayOutput() {
        for (int i = 0; i < layers[layers.length - 1].numberofNeurons; i++) {
            System.out.println(Math.round(layers[layers.length - 1].neurons[i].output));
        }
    }

    public void startLearning(double[] inputs, double[] outputs) {
        setInput(inputs);
        feedForward();
        countMistakes(outputs);
        changeWeights();
    }

    private void setLayers(int[] sizes) {
        layers = new Layer[sizes.length];
        for (int i = 0; i < sizes.length; i++) {
            layers[i] = new Layer(sizes[i]);
        }
    }

    private void setWeights() {
        for (int i = 0; i < layers.length - 1; i++) {
            layers[i].weights = new double[layers[i].numberofNeurons][layers[i + 1].numberofNeurons];
            for (int j = 0; j < layers[i].weights.length; j++) {
                for (int k = 0; k < layers[i].weights[j].length; k++) {
                    layers[i].weights[j][k] = Math.random();
                }
            }
        }
        //Setting bias weight array
        for (int i = 0; i < layers.length - 1; i++) {
            layers[i].biasWeights = new double[layers[i + 1].numberofNeurons];
            for (int j = 0; j < layers[i + 1].numberofNeurons; j++) {
                layers[i].biasWeights[j] = Math.random();
            }
        }
    }

    private void setInput(double[] inputs) {
        layers[0].setInput(inputs);
    }

    private void feedForward() {
        for (int i = 1; i < layers.length; i++) {
            for (int j = 0; j < layers[i].numberofNeurons; j++) {
                double sum = 0;
                for (int k = 0; k < layers[i - 1].numberofNeurons; k++) {
                    sum += layers[i - 1].neurons[k].output * layers[i - 1].weights[k][j];
                }
                //Adding bias weight * 1
                sum += layers[i - 1].biasWeights[j];
                //Setting input and getting output
                layers[i].neurons[j].input = sum;
                layers[i].neurons[j].countAndSetOutput();
            }
        }
    }

    private void countMistakes(double[] expected) {
        if (expected.length != layers[layers.length - 1].numberofNeurons) {
            System.out.println("Некорректно введены ожидаемые выходные данные");
            return;
        }
        double countMis = 0;
        for (int i = 0; i < expected.length; i++) {
            layers[layers.length - 1].neurons[i].mistake = expected[i] - layers[layers.length - 1].neurons[i].output;
            countMis += layers[layers.length - 1].neurons[i].mistake*layers[layers.length - 1].neurons[i].mistake;
        }
        System.out.println("Ошибка: "+ countMis);
        for (int i = layers.length - 2; i >= 0; i--) {
            //Count each neuron`s mistake
            for (int j = 0; j < layers[i].numberofNeurons; j++) {
                double sumMis = 0;
                for (int k = 0; k < layers[i + 1].numberofNeurons; k++) {
                    sumMis += layers[i].weights[j][k] * layers[i + 1].neurons[k].mistake;
                }
                layers[i].neurons[j].mistake = sumMis;
            }
        }
    }

    private void changeWeights() {
        for (int i = 0; i < layers.length - 1; i++) {
            for (int j = 0; j < layers[i].numberofNeurons; j++) {
                for (int k = 0; k < layers[i + 1].numberofNeurons; k++) {
                    layers[i].weights[j][k] += coef * layers[i + 1].neurons[k].mistake
                            * layers[i + 1].neurons[k].output
                            * (1 - layers[i + 1].neurons[k].output)
                            * layers[i].neurons[j].output;
                }
            }
        }
        //Changing bias weight array
        for (int i = 0; i < layers.length - 1; i++) {
            for (int j = 0; j < layers[i + 1].numberofNeurons; j++) {
                layers[i].biasWeights[j] += coef * layers[i + 1].neurons[j].mistake
                        * layers[i + 1].neurons[j].output
                        * (1 - layers[i + 1].neurons[j].output);
            }
        }
    }
}
