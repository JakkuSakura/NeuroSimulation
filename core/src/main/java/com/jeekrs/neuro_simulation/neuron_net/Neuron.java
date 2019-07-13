package com.jeekrs.neuro_simulation.neuron_net;

import com.jeekrs.neuro_simulation.interfaces.MyCloneable;

/**
 * 神经元
 * @author huangyundu
 *
 */
public class Neuron implements MyCloneable {
	
	/**
	 * 神经元值
	 */
    public float value;
	/**
	 * 神经元输出值
	 */
    public float o;
	
	public Neuron () {
		init ();
	}

    public Neuron(float v) {
		init (v);
	}

    public Neuron(float v, float o) {
		this.value = v;
		this.o = o;
	}
	
	public void init () {
		this.value = 0;
		this.o = 0;
	}

    public void init(float v) {
		this.value = v;
		this.o = 0;
	}

    public void init(float v, float o) {
		this.value = v;
		this.o = o;
	}
	
	/**
	 * sigmod激活函数
	 */
	public void sigmoid() {
        this.o = (float) (1.0 / (1.0 + Math.exp(-1.0 * this.value)));
	}
	
	public String toString () {
		return "(" + value + " " + o + ")";
	}

    @Override
    public Neuron clone() {
        try {
            return (Neuron) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
