import javax.swing.JOptionPane;

/*һ���У���������Ҫ������Դ���У�����Դ*/
public class Bank {
	// ÿ����������Ҫ�������Դ��
	public static int MAX[][] = { { 7, 5, 3 }, { 3, 2, 2 }, { 9, 0, 2 }, { 2, 2, 2 }, { 4, 3, 3 } };
	// ϵͳӵ�еĳ�ʼ��Դ��
	public static int AVAILABLE[] = { 10, 5, 7 };
	// ϵͳ�Ѹ�ÿ�����̷������Դ��
	public static int ALLOCATION[][] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
	// ÿ�����̻���Ҫ����Դ��
	public static int NEED[][] = { { 7, 5, 3 }, { 3, 2, 2 }, { 9, 0, 2 }, { 2, 2, 2 }, { 4, 3, 3 } };
	// ÿ���������Դ��
	public static int Request[] = { 0, 0, 0 };
	// ����������Դ��
	public static int M = 5, N = 3;
	int FALSE = 0;
	int TRUE = 1;

	public void showdata() {
		int i, j;
		System.out.print("ϵͳ���õ���Դ��Ϊ:/n");
		for (j = 0; j < N; j++) {
			System.out.print("��Դ" + j + ":" + AVAILABLE[j] + " ");
		}
		System.out.println();
		System.out.println("�����̻���Ҫ����Դ��:");
		for (i = 0; i < M; i++) {
			System.out.print("����" + i + ":");
			for (j = 0; j < N; j++) {
				System.out.print("��Դ" + j + ":" + NEED[i][j] + " ");
			}
			System.out.print("/n");
		}
		System.out.print("�������Ѿ��õ�����Դ��: /n");
		for (i = 0; i < M; i++) {
			System.out.print("����");
			System.out.print(i);
			for (j = 0; j < N; j++) {
				System.out.print("��Դ" + j + ":" + ALLOCATION[i][j] + " ");
			}
			System.out.print("/n");
		}
	}

	// ������Դ�������¸��¸���״̬
	public void changdata(int k) {
		int j;
		for (j = 0; j < N; j++) {
			AVAILABLE[j] = AVAILABLE[j] - Request[j];
			ALLOCATION[k][j] = ALLOCATION[k][j] + Request[j];
			NEED[k][j] = NEED[k][j] - Request[j];
		}
	};

	// ������Դ�������¸��¸���״̬
	public void rstordata(int k) {
		int j;
		for (j = 0; j < N; j++) {
			AVAILABLE[j] = AVAILABLE[j] + Request[j];
			ALLOCATION[k][j] = ALLOCATION[k][j] - Request[j];
			NEED[k][j] = NEED[k][j] + Request[j];
		}
	};

	// �ͷ���Դ
	public void free(int k) {
		for (int j = 0; j < N; j++) {
			AVAILABLE[j] = AVAILABLE[j] + ALLOCATION[k][j];
			System.out.print("�ͷ�" + k + "�Ž��̵�" + j + "��Դ!/n");
		}
	}

	public int check0(int k) {
		int j, n = 0;
		for (j = 0; j < N; j++) {
			if (NEED[k][j] == 0)
				n++;
		}
		if (n == 3)
			return 1;
		else
			return 0;
	}

	// ��鰲ȫ�Ժ���
	public int chkerr(int s) {
		int WORK;
		int FINISH[] = new int[M], temp[] = new int[M];// ������ʱ�İ�ȫ��������
		int i, j, k = 0;
		for (i = 0; i < M; i++)
			FINISH[i] = FALSE;
		for (j = 0; j < N; j++) {
			WORK = AVAILABLE[j]; // ��j����Դ������
			i = s;
			// �жϵ�i�������Ƿ���������
			while (i < M) {
				if (FINISH[i] == FALSE && NEED[i][j] <= WORK) {
					WORK = WORK + ALLOCATION[i][j];
					FINISH[i] = TRUE;
					temp[k] = i;
					k++;
					i = 0;
				} else {
					i++;
				}
			}
			for (i = 0; i < M; i++)
				if (FINISH[i] == FALSE) {
					System.out.print("/nϵͳ����ȫ!!! ������Դ���벻�ɹ�!/n");
					return 1;
				}
		}
		System.out.print("/n����ȫ�Լ�飬ϵͳ��ȫ�����η���ɹ���/n");
		System.out.print("���ΰ�ȫ���У�");
		for (i = 0; i < M - 1; i++) {
			System.out.print("����" + temp[i] + "->");
		}
		System.out.print("����" + temp[M - 1]);
		System.out.println("/n");
		return 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 0, j = 0;
		int flag = 1;
		Bank bank = new Bank();
		bank.showdata();
		while (flag == 1) {
			i = -1;
			while (i < 0 || i >= M) {
				String str = JOptionPane.showInputDialog("��������������Դ�Ľ��̺ţ���0��" + (M - 1) + "������������!��");
				i = Integer.parseInt(str);
				if (i < 0 || i >= M)
					System.out.println("����Ľ��̺Ų����ڣ���������!/n");
			}
			System.out.print("���������" + i + "�������Դ��/n");
			for (j = 0; j < N; j++) {
				String str = JOptionPane.showInputDialog("��Դ" + j + ":");
				Request[j] = Integer.parseInt(str);
				if (Request[j] > NEED[i][j]) {
					System.out.print("����" + i + "�������Դ�����ڽ���" + i + "����Ҫ" + j + "����Դ����Դ��!���벻��������!������ѡ��!\n");
					flag = 0;
					break;
				} else {
					if (Request[j] > AVAILABLE[j]) {
						System.out.print("����" + i + "�������Դ������ϵͳ����" + j + "����Դ����Դ��!���벻��������!������ѡ��!/n");
						flag = 0;
						break;
					}
				}
			}
			if (flag == 1) {
				bank.changdata(i);
				int chkerr = bank.chkerr(i);
				if (chkerr == 1) {
					bank.rstordata(i);
					bank.showdata();
				} else {
					bank.showdata();
					int check = bank.check0(i);
					if (check == 1) {
						System.out.println("����" + i + "�Ѿ���ɣ�ϵͳ������ռ����Դ�ͷ�.\n");
						bank.free(i);
					}
				}
			} else {
				bank.showdata();
			}
			System.out.println("\n");
			String str = JOptionPane.showInputDialog("�Ƿ�������м��㷨��ʾ,��'1'������,��'0'���˳���ʾ");
			flag = Integer.parseInt(str);
		}
	}
}