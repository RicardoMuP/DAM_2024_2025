package es.florida.psp_aev1;

public class SimulacionMT implements Runnable {

	private int tipo;

	public SimulacionMT(int tipo) {
		this.tipo = tipo;
	}

	/**
	 *método que implementa la interfaz Runnable.
	 */
	@Override
	public void run() {
		long tiempoInicio = System.currentTimeMillis();
		double result = SimulacionMP.simulation(tipo);
		long tiempoFinal = System.currentTimeMillis();
		System.out.println("Simulación MT de proteína tipo " + tipo + " completada. Tiempo: "
				+ (tiempoFinal - tiempoInicio) + " ms.");
	}

	/**
	 * método para simular las proteínas con multihilo
	 * @param primaria: número de proteínas primarias que queremos simular 
	 * @param secundaria: número de proteínas secundarias que queremos simular
	 * @param terciaria: número de proteínas terciarias que queremos simular
	 * @param cuaternaria: número de proteínas cuaternarias que queremos simular
	 */
	public static void simularProteinaMT(int primaria, int secundaria, int terciaria, int cuaternaria) {

		if (primaria > 0) {
			for (int i = 0; i < primaria; i++) {
				try {
					new Thread(new SimulacionMT(1)).start();
					// System.out.println("Simulación MT de proteína primaria " + (i + 1) + "
					// completada.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (secundaria > 0) {
			for (int i = 0; i < secundaria; i++) {
				try {
					new Thread(new SimulacionMT(2)).start();
					// System.out.println("Simulación MT de proteína secundaria " + (i + 1) + "
					// completada.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (terciaria > 0) {
			for (int i = 0; i < terciaria; i++) {
				try {
					new Thread(new SimulacionMT(3)).start();
					// System.out.println("Simulación MT de proteína terciaria " + (i + 1) + "
					// completada.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (cuaternaria > 0) {
			for (int i = 0; i < cuaternaria; i++) {
				try {
					new Thread(new SimulacionMT(4)).start();
					// System.out.println("Simulación MT de proteína cuaternaria " + (i + 1) + "
					// completada.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
