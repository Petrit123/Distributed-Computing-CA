
public class HomeScreenClass {
	
	public static void main(String[] args) {
		
		HomeScreen2 window = new HomeScreen2();
		window.frame.setVisible(true);
		
		try {
			for (int i = 0; i <= 100; i++) {
				Thread.sleep(30);
				window.lblNewLabel.setText(Integer.toString(i));
				window.progressBar.setValue(i);
				if (i == 100) {
					window.frame.setVisible(false);
					ConnectToServerForm frame = new ConnectToServerForm();
					frame.setVisible(true);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
