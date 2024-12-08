package es.florida.psp_aev1;

import java.io.IOException;

public class SimulacionMP {

	/**
	 * método para ejecutar la simulacion por multiproceso
	 * @param args
	 */
	public static void main(String[] args) {
		int tipo = Integer.parseInt(args[0]);
		double result = simulation(tipo);
		simulation(tipo);
		// System.out.println("Resultado de la simulación MP: " + result);
	}

	/**
	 * método predefinido que hace calculos según el tipo de proteína
	 * @param tipo: tipo de proteína
	 * @return calc: resultado del cálculo
	 */
	public static double simulation(int tipo) {
		double calc = 0.0;
		double simulationTime = Math.pow(5, tipo);
		double startTime = System.currentTimeMillis();
		double endTime = startTime + simulationTime;
		while (System.currentTimeMillis() < endTime) {
			calc = Math.sin(Math.pow(Math.random(), 2));
		}
		return calc;
	}

	/**
	 * método para simular las proteínas con multiproceso
	 * @param primaria: número de proteínas primarias
	 * @param secundaria: número de proteínas secundarias
	 * @param terciaria: número de proteínas terciarias
	 * @param cuaternaria: número de proteínas cuaternarias
	 */
	public static void simularProteinaMP(int primaria, int secundaria, int terciaria, int cuaternaria) {
		if (primaria > 0) {
			for (int i = 0; i < primaria; i++) {
				try {
					long tiempoInicio = System.currentTimeMillis();
					ProcessBuilder builder = new ProcessBuilder("java", "SimulacionMP", "1");
					Process process = builder.start();
					process.waitFor();
					long tiempoFinal = System.currentTimeMillis();
					System.out.println("Simulación MP de proteína primaria " + (i + 1) + " completada. Tiempo: "
							+ (tiempoFinal - tiempoInicio) + " ms.");
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		if (secundaria > 0) {
			for (int i = 0; i < secundaria; i++) {
				try {
					long tiempoInicio = System.currentTimeMillis();
					ProcessBuilder builder = new ProcessBuilder("java", "SimulacionMP", "2");
					Process process = builder.start();
					process.waitFor();
					long tiempoFinal = System.currentTimeMillis();
					System.out.println("Simulación MP de proteína secundaria " + (i + 1) + " completada. Tiempo: "
							+ (tiempoFinal - tiempoInicio) + " ms.");
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		if (terciaria > 0) {
			for (int i = 0; i < terciaria; i++) {
				try {
					long tiempoInicio = System.currentTimeMillis();
					ProcessBuilder builder = new ProcessBuilder("java", "SimulacionMP", "3");
					Process process = builder.start();
					process.waitFor();
					long tiempoFinal = System.currentTimeMillis();
					System.out.println("Simulación MP de proteína terciaria " + (i + 1) + " completada. Tiempo: "
							+ (tiempoFinal - tiempoInicio) + " ms.");
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		if (cuaternaria > 0) {
			for (int i = 0; i < cuaternaria; i++) {
				try {
					long tiempoInicio = System.currentTimeMillis();
					ProcessBuilder builder = new ProcessBuilder("java", "SimulacionMP", "4");
					Process process = builder.start();
					process.waitFor();
					long tiempoFinal = System.currentTimeMillis();
					System.out.println("Simulación MP de proteína cuaternaria " + (i + 1) + " completada. Tiempo: "
							+ (tiempoFinal - tiempoInicio) + " ms.");
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
