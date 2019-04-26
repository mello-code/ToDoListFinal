package maintodo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class Add extends javax.swing.JFrame
{
	public Add()
	{
		setType(Type.POPUP);
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("Add a new item");
		initComponents();
	}

	// Do not modify this
	private void initComponents()
	{
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		descriptionTextField = new javax.swing.JTextField();
		descriptionTextField.setBackground(Color.WHITE);
		dueDateLabel = new javax.swing.JLabel();
		dueDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dueDateMonthTextField = new javax.swing.JTextField();
		priorityLabel = new javax.swing.JLabel();
		priorityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dueDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		priorityTextField = new javax.swing.JTextField();
		inProgressButton = new javax.swing.JRadioButton();
		buttonGroup.add(inProgressButton);
		notStartedButton = new javax.swing.JRadioButton();
		buttonGroup.add(notStartedButton);
		completedButton = new javax.swing.JRadioButton();
		buttonGroup.add(completedButton);

		saveButton = new javax.swing.JButton();
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
			}
		});
		saveButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
				boolean priorityCheck = CheckInt(priorityTextField.getText());
				boolean dateDayCheck = CheckInt(dueDateMonthTextField.getText());
				boolean dateMonthCheck = CheckInt(dueDateDayTextField.getText());
				descriptionTextField.setBorder(new LineBorder(Color.GRAY, 1));
				dueDateMonthTextField.setBorder(new LineBorder(Color.GRAY, 1));
				dueDateDayTextField.setBorder(new LineBorder(Color.GRAY, 1));
				priorityTextField.setBorder(new LineBorder(Color.GRAY, 1));

				if (descriptionTextField.getText().equals(""))
				{
					errorLabel.setText("Description field cannot be empty!");
					descriptionTextField.setBorder(new LineBorder(Color.RED, 2));
				}
				else if (duplicateCheck(descriptionTextField.getText().replaceAll("\\s+", "")))
				{
					errorLabel.setText("Identical description already exists!");
					descriptionTextField.setBorder(new LineBorder(Color.RED, 2));
				}
				else if (dueDateMonthTextField.getText().equals(""))
				{
					errorLabel.setText("Date field cannot be empty!");
					dueDateMonthTextField.setBorder(new LineBorder(Color.RED, 2));
				}
				else if (dueDateDayTextField.getText().equals(""))
				{
					errorLabel.setText("Date field cannot be empty!");
					dueDateDayTextField.setBorder(new LineBorder(Color.RED, 2));
				}
				else if (priorityTextField.getText().equals(""))
				{
					errorLabel.setText("Priority field cannot be empty!");
					priorityTextField.setBorder(new LineBorder(Color.RED, 2));
				}
				else if (!dateDayCheck)
				{
					errorLabel.setText("Date must be an integer!");
					dueDateMonthTextField.setBorder(new LineBorder(Color.RED, 2));
				}
				else if (!dateMonthCheck)
				{
					errorLabel.setText("Date must be an integer!");
					dueDateDayTextField.setBorder(new LineBorder(Color.RED, 2));
				}
				else if (formatDate(Integer.parseInt(dueDateMonthTextField.getText()), Integer.parseInt(dueDateDayTextField.getText())).equals(""))
				{
					errorLabel.setText("Enter a valid date!");
					dueDateDayTextField.setBorder(new LineBorder(Color.RED, 2));
					dueDateMonthTextField.setBorder(new LineBorder(Color.RED, 2));
				}
				else if (!priorityCheck)
				{
					errorLabel.setText("Priority must be a positive integer!");
					priorityTextField.setBorder(new LineBorder(Color.RED, 2));
				}
				else if (Integer.parseInt(priorityTextField.getText()) < 1)
				{
					errorLabel.setText("Priority must be greater than 0!");
					priorityTextField.setBorder(new LineBorder(Color.RED, 2));
				}
				else if (!notStartedButton.isSelected() && !inProgressButton.isSelected() && !completedButton.isSelected())
					errorLabel.setText("A status must be selected!");
				else
				{
					// Save item to list and close this window
					Item item = new Item();

					item.setDescription(descriptionTextField.getText());
					item.setDueDate(formatDate(Integer.parseInt(dueDateMonthTextField.getText()), Integer.parseInt(dueDateDayTextField.getText())));

					if (notStartedButton.isSelected())
					{
						item.setStatus(Status.NOT_STARTED);
						item.setOptionalDate("-");

						Main.addToList(item, Integer.parseInt(priorityTextField.getText()) -1);
					}
					else
					{
						Date today = new Date();
						Calendar cal = Calendar.getInstance();
						cal.setTime(today);

						item.setOptionalDate(formatDate(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE)));

						if (inProgressButton.isSelected())
						{
							item.setStatus(Status.IN_PROGRESS);

							Main.addToList(item, Integer.parseInt(priorityTextField.getText()) - 1);
						}
						else
						{
							// item.setPriority();
							item.setStatus(Status.COMPLETED);

							Main.addToList(item, -1);
						}

					}

					setVisible(false);
					dispose();
				}
			}
		});

		cancelButton = new javax.swing.JButton();
		cancelButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
				setVisible(false);
				dispose();
			}
		});

		detailLabel = new JLabel(
				"Details  \u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014");

		decriptionLabel = new JLabel();
		decriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		decriptionLabel.setText("Description:");

		dueDateLabel.setText("Due Date:");
		slash = new JLabel();
		slash.setText("/");
		dueDateDayTextField = new JTextField();

		priorityLabel.setText("Priority:");

		statusLabel = new JLabel();
		statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		statusLabel.setText("Status:");

		inProgressButton.setText("In Progress");
		notStartedButton.setText("Not Started");
		completedButton.setText("Completed");

		errorLabel = new JLabel("                                         ");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.RED);

		saveButton.setText("Save");
		cancelButton.setText("Cancel");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(detailLabel).addPreferredGap(ComponentPlacement.RELATED))
				.addGroup(layout.createSequentialGroup().addGap(67).addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(errorLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup().addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
								.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
						.addGap(42)))
				.addGap(30))
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(priorityLabel, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
								.addComponent(dueDateLabel, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
								.addComponent(decriptionLabel, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
								.addComponent(statusLabel, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED, 7, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(1)
										.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(notStartedButton)
												.addComponent(inProgressButton, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
												.addComponent(completedButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup().addGap(6)
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(descriptionTextField, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(priorityTextField)
																.addGroup(layout.createSequentialGroup()
																		.addComponent(dueDateMonthTextField, GroupLayout.PREFERRED_SIZE, 36,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(6).addComponent(slash)))
														.addPreferredGap(ComponentPlacement.RELATED).addComponent(dueDateDayTextField,
																GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))))
						.addGap(82)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(detailLabel).addGap(11)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(decriptionLabel).addComponent(descriptionTextField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(dueDateLabel)
								.addComponent(dueDateMonthTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(dueDateDayTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(slash))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(priorityTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(priorityLabel))
						.addGap(8).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(notStartedButton).addComponent(statusLabel))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(inProgressButton).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(completedButton).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(errorLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(32, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public boolean CheckInt(String check)
	{
		try
		{
			Integer.parseInt(check);
		} catch (NumberFormatException ex)
		{
			return false;
		}
		return true;
	}

	public boolean duplicateCheck(String descriptionWithoutSpaces)
	{
		ArrayList<Item> list = Main.fetchItems();
		for (Item item : list)
		{
			if (item.getDescription().replaceAll("\\s+", "").equals(descriptionWithoutSpaces))
				return true;
		}
		return false;
	}

	private String formatDate(int month, int day)
	{
		if (day < 1 || month < 1 || month > 12)
			return "";
		else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day > 31)
			return "";
		else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)
			return "";
		else if (month == 2 && day > 28)
			return "";
		else
		{
			String dayS = day + "";
			String monthS = month + "";
			if (day < 10)
				dayS = "0" + dayS;
			if (month < 10)
				monthS = "0" + monthS;
			return monthS + "/" + dayS;
		}
	}

	public static void main(String args[])
	{
		try
		{
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		} catch (Throwable e)
		{
			e.printStackTrace();
		}
		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
			if ("Nimbus".equals(info.getName()))
				break;

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new Add().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton saveButton;
	private javax.swing.JButton cancelButton;
	private javax.swing.JLabel dueDateLabel;
	private javax.swing.JLabel priorityLabel;
	private javax.swing.JLabel errorLabel;
	private javax.swing.JRadioButton inProgressButton;
	private javax.swing.JRadioButton notStartedButton;
	private javax.swing.JRadioButton completedButton;
	private javax.swing.JTextField descriptionTextField;
	private javax.swing.JTextField dueDateMonthTextField;
	private javax.swing.JTextField dueDateDayTextField;
	private javax.swing.JTextField priorityTextField;
	private JLabel detailLabel;
	private JLabel decriptionLabel;
	private JLabel slash;
	private JLabel statusLabel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
}
