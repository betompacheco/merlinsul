package com.merlin.util;

import org.junit.Test;

import junit.framework.Assert;

public class UtilitarioTest {

	@Test
	public void testFormataCpfCnpj() {
		Assert.assertEquals("000.000.000-00", Utilitario.formataCpfCnpj("00000000000"));
		Assert.assertEquals("35.485.168/0001-19", Utilitario.formataCpfCnpj("35485168000119"));
	}
}
