package edu.tallerweb.cuentas;

/**
 * Es el tipo de cuenta más simple, ya que se rige por la premisa
 * de que en tanto y en cuanto se tenga tanto o más dinero en
 * cuenta del que se quiere extraer, la operación se debe efectuar
 * correctamente.
 */
public class CuentaSueldo {
	
	private Double saldo = 0.0;

	/**
	 * No hay reglas adicionales para el depósito
	 * @param monto a depositar
	 */
	public void depositar(final Double monto) {
		
		if (monto < 0) {
			throw new CuentaBancariaException("No se puede depositar monto negativo");
		}

		this.saldo += monto;
	}

	/**
	 * No hay reglas adicionales para la extracción
	 * @param monto a extraer
	 */
	public void extraer(final Double monto) {
		if (monto < 0) {
			throw new CuentaBancariaException("No se puede extraer monto negativo");
		}
		
		if(this.saldo < monto) {
			
			throw new CuentaBancariaException("Su Saldo insuficiente para realizar esta extraccion");

		}

		this.saldo -= monto;
	}

	/**
	 * Permite saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */
	public Double getSaldo() {
		return this.saldo;
//		throw new RuntimeException("No implementado aún");
	}

}
