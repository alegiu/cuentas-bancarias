package edu.tallerweb.cuentas;

/**
 * La más compleja de las cuentas, ésta permite establecer una
 * cantidad de dinero a girar en descubierto. Es por ello que
 * cada vez que se desee extraer dinero, no sólo se considera
 * el que se posee, sino el límite adicional que el banco
 * estará brindando.
 *
 * Por supuesto esto no es gratis, ya que el banco nos cobrará
 * un 5% como comisión sobre todo el monto en descubierto
 * consumido en la operación.
 *
 * Por ejemplo, si tuviéramos $ 100 en la cuenta, y quisiéramos
 * retirar $ 200 (con un descubierto de $ 150), podremos hacerlo.
 * Pasaremos a deberle al banco $ 105 en total: los $ 100 que
 * nos cubrió, más el 5% adicional sobre el descubierto otorgado.
 */
public class CuentaCorriente {

	/**
	 * Toda cuenta corriente se inicia con un límite total
	 * para el descubierto.
	 * @param descubiertoTotal
	 */
	private Double saldo = 0.0;
	private Double descubiertoTotal = 0.0;
	private Double descubierto = 0.0;
	private Double monto = 0.0;
	private Double recargoDescubierto = 1.05;
	
	public CuentaCorriente(final Double descubiertoTotal) {
		if (descubiertoTotal < 0) {
			throw new CuentaBancariaException("El DescubiertoLimite debe ser mayor a 0 (CERO)");
		}

		this.descubiertoTotal = descubiertoTotal;
		this.descubierto = descubiertoTotal;
	}
	/**
	 * Todo depósito deberá cubrir primero el descubierto,
	 * si lo hubiera, y luego contar para el saldo de la
	 * cuenta.
	 * @param monto a depositar
	 */
	public void depositar(final Double monto) {
		if (monto < 0) {
			throw new CuentaBancariaException("No se puede depositar monto negativo");
		}

		this.monto = monto;

		if ((this.descubiertoTotal - this.descubierto) == 0) {

			this.saldo += this.monto;

		} else {

			if ((this.descubiertoTotal - this.descubierto) < this.monto) {

				this.monto -= (this.descubiertoTotal - this.descubierto);
				this.descubierto = this.descubiertoTotal;
				this.saldo += this.monto;

			} else {

				this.descubierto += this.monto;

			}


		}
	}

	/**
	 * Se cobrará el 5% de comisión sobre el monto girado
	 * en descubierto.
	 * Por supuesto, no puede extraerse más que el total
	 * de la cuenta, más el descubierto (comisión incluída)
	 * @param monto a extraer
	 */
	public void extraer(final Double monto) {
		if (monto < 0) {
			throw new CuentaBancariaException("No se puede extraer monto negativo");
		}

		this.monto = monto;

		if ((this.saldo + this.descubierto) < this.monto) {

			throw new CuentaBancariaException("Saldo insuficiente para realizar esta extraccion");
		}

		if (this.saldo < this.monto) {

			this.monto -= this.saldo;
			this.saldo = 0.0;
			this.descubierto -= this.monto * recargoDescubierto;

		} else {

			this.saldo -= this.monto;
		}
	}

	/**
	 * Permite saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */
	public Double getSaldo() {
		return this.saldo;
	}
	/**
	 * Permite saber el saldo en descubierto
	 * @return el descubierto de la cuenta
	 */
	public Double getDescubierto() {
		return this.descubierto;
	}

}
