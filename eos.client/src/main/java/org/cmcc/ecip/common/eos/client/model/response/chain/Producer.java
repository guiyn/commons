package org.cmcc.ecip.common.eos.client.model.response.chain;


import org.cmcc.ecip.common.eos.client.model.response.chain.producer.Active;
import org.cmcc.ecip.common.eos.client.model.response.chain.producer.Pending;
import org.cmcc.ecip.common.eos.client.model.response.chain.producer.Proposed;




public class Producer {


    private Active active;


    private Pending pending;

    private Proposed proposed;

	public Active getActive() {
		return active;
	}

	public void setActive(Active active) {
		this.active = active;
	}

	public Pending getPending() {
		return pending;
	}

	public void setPending(Pending pending) {
		this.pending = pending;
	}

	public Proposed getProposed() {
		return proposed;
	}

	public void setProposed(Proposed proposed) {
		this.proposed = proposed;
	}
}
