package edu.tallerweb.cuentas;

/**
 * Similar a la CuentaSueldo, pero se pide que luego de la
 * quinta extracción de dinero se cobre un costo adicional
 * por extracción de $ 6
 */
public class CajaAhorros {

	/**
	 * No hay reglas adicionales para el depósito
	 * @param monto a depositar
	 */
	private Double saldo = 0.0;
	private Integer cantidadDeExtracciones = 0;
	private Double adicionalPorExtraccion = 0.0;

	public void depositar(final Double monto) {
		this.saldo += monto;
//		throw new RuntimeException("No implementado aún");
	}

	/**
	 * Se cobran $6 adicionales por cada extracción luego de
	 * la quinta.
	 * @param monto a extraer
	 */
	public void extraer(final Double monto) {
		final Double seis = 6.0;
		final Integer limiteDeExtraccionesGratis = 5;
		cantidadDeExtracciones += 1;
		if (this.cantidadDeExtracciones > limiteDeExtraccionesGratis) {
			this.adicionalPorExtraccion = seis;
		}

		if ((this.saldo - this.adicionalPorExtraccion) < monto) {
			throw new CuentaBancariaException("Saldo insuficiente para realizar esta extraccion");
		}

		this.saldo -= monto + this.adicionalPorExtraccion;
//		throw new RuntimeException("No implementado aún");
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
