package com.seshenghuo.jfx.mina;

import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import javax.swing.JFileChooser;

import com.seshenghuo.logger.L;
import com.seshenghuo.mina.MinaClientConfig;
import com.seshenghuo.mina.fs.Commands;
import com.seshenghuo.mina.fs.FileClient;
import com.seshenghuo.mina.fs.RemainingNotify;
import com.seshenghuo.util.Util;

public class FileClientUI extends Application {
	List<File> fileList = null;
	ArrayList<File> ignoreList = new ArrayList<File>();
	ArrayList<File> allowList = new ArrayList<File>();
	ArrayList<File> waitingList = new ArrayList<File>();
	TableView<FileInfo> table = null;
	ComboBox<String> siteCombo = null;
	ComboBox<String> serverCombo = null;
	Button browser = null;
	Button upload = null;
	Button clear = null;
	ProgressBar bar = null;
	GridPane barGrid = null;
	Text barLabel = null;

	Text netStatusText = null;
	Text transformText = null;
	Text messageText = null;

	Timer timer = null;

	int select_size = 0;
	FileClient client = new FileClient();
	SimpleRemainingNotify notify = new SimpleRemainingNotify();

	String dateString = Util.getDateString("yyyyMMdd");
	String sServerList = MinaClientConfig.getStringValue("serverlist");
	String[] serverList = sServerList.split("\\|");
	String serverKey = serverList[0];
	String serverHost = MinaClientConfig.getStringValue("host." + serverKey,
			"127.0.0.1");
	int serverPort = MinaClientConfig.getIntValue("port." + serverKey, 8000);
	String sites = MinaClientConfig.getStringValue("sites");
	String[] siteList = sites.split("\\|");
	String site = siteList.length > 0 ? siteList[0] : "root";
	String localRoot = MinaClientConfig.getStringValue("local." + site);
	String remoteRoot = MinaClientConfig.getStringValue("remote." + site);
	long max_length = MinaClientConfig.getLongValue("upload_max_length");
	int max_size = MinaClientConfig.getIntValue("upload_max_size");
	int width = MinaClientConfig.getIntValue("jfx.width");
	int height = MinaClientConfig.getIntValue("jfx.height");

	boolean isRejected = false;

	class SimpleRemainingNotify implements RemainingNotify {
		int count = 0;
		int total = 0;
		Text _label;
		ProgressBar _bar;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.seshenghuo.jfx.mina.FileClientUI.SimpleRemainingNotify#message
		 * (com.seshenghuo.mina.fs.FileClient, java.lang.String, int, int)
		 */
		@Override
		public void message(FileClient client, int cmd, int remain, int pckSize) {
			// TODO Auto-generated method stub

			synchronized (client) {
				// System.out.println("ui message: " + cmd + "://" + remain +
				// "/" + pckSize);
				double percent = ((double) remain / (double) pckSize);

				// _label.setText(count + "/" + total);
				_bar.setProgress(percent);

				setMessageText("(" + count + "/" + total + ")上传中...");

				if (cmd == Commands.QUIT) {
					count++;
					_label.setText((count + "/" + total));

					if (count >= total) {
						// client.disconnect();
						// setNetStatutText("重置连接", "red");

						setMessageText("上传完毕");
						setClientStatutText("空闲", "black");

						calcList();

					}
					// System.out.println(count + "/" + total);
				} else if (cmd == Commands.REJECTED) {
					setClientStatutText("拒绝", "red");
					setNetStatutText("Not Allowed", "red");
					setMessageText("服务器拒绝你的请求");
					isRejected = true;
				}

				L.info("FSC", SimpleRemainingNotify.class, "message()", "INFO",
						"(" + cmd + ")progress: " + count + "/" + total);
			}

		}

		private void calcList() {
			int wsize = waitingList.size();

			if (wsize > 0) {
				delay(200);
				setMessageText("正在重新计算文件列表...");
				setClientStatutText("计算", "blue");
				delay(200);
				allowList.clear();

				int length = Math.min(max_size, wsize);
				for (int i = 0; i < wsize && i < length; i++) {
					allowList.add(waitingList.get(i));
				}
				waitingList.removeAll(waitingList.subList(0, length));
				insertTableViewData(table);

				select_size = length;

				setMessageText("等待上传文件  " + total + " 个文件，等待("
						+ waitingList.size() + ")");
				setClientStatutText("空闲", "black");

				delay(200);
				upload();

			} else {
				select_size = 0;
				allowList.clear();
				insertTableViewData(table);
				disabled(false);
				barGrid.setVisible(false);
			}
		}

		/**
		 * @param _label
		 *            the _label to set
		 */
		private void set_label(Text _label) {
			this._label = _label;
		}

		/**
		 * @param _bar
		 *            the _bar to set
		 */
		private void set_bar(ProgressBar _bar) {
			this._bar = _bar;
		}

		private void setCount(int count) {
			this.count = count;
		}

		private void setTotal(int total) {
			this.total = total;
		}

	}

	class CellFactory
			implements
			Callback<TableColumn<FileInfo, String>, TableCell<FileInfo, String>> {
		private Pos pos = null;

		public CellFactory(Pos pos) {
			this.pos = pos;
		}

		@Override
		public TableCell<FileInfo, String> call(
				TableColumn<FileInfo, String> arg0) {
			// TODO Auto-generated method stub
			TableCell<FileInfo, String> cell = new TableCell<FileInfo, String>() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see javafx.scene.control.Cell#updateItem(java.lang.Object,
				 * boolean)
				 */
				@Override
				protected void updateItem(String arg0, boolean arg1) {
					// TODO Auto-generated method stub
					super.updateItem(arg0, arg1);

					setText(arg1 ? null : getString());
					setGraphic(null);
				}

				private String getString() {
					return getItem() == null ? "" : getItem().toString();
				}

			};
			cell.setAlignment(pos);
			return cell;
		}

	}

	private void disabled(boolean dis) {
		serverCombo.setDisable(dis);
		siteCombo.setDisable(dis);
		browser.setDisable(dis);
		upload.setDisable(dis);
		clear.setDisable(dis);
		table.setDisable(dis);
	}

	private void upload() {
		bar.setProgress(0D);

		if (null != allowList && allowList.size() > 0) {
			File file = null;
			String parentDir = "";
			String relativeDir = "";

			int total = allowList.size();
			barLabel.setText("0/" + total);
			notify.setCount(0);
			notify.setTotal(total);

			barGrid.setVisible(true);

			for (int i = 0, size = allowList.size(); i < size; i++) {
				file = allowList.get(i);

				parentDir = file.getParent();
				relativeDir = getRelativeDir(parentDir);

				String remotePath = "";
				remotePath = remoteRoot + relativeDir + "/" + file.getName();

				L.info("FSC", FileClientUI.class, "upload()", "INFO",
						"the remote path is " + remotePath);

				client.mount(client.newEntity(Commands.UPLOAD, remotePath,
						file.getAbsolutePath(), "", file));
			}

			if (!client.isConnected()) {
				client.connect();
				disabled(false);
				setClientStatutText("等待...", "blue");
				setMessageText("尝试连接服务器...");
			} else {
				notify.setCount(0);
				client.sendData();
				disabled(true);
				setClientStatutText("工作中...", "green");
				setMessageText("正在发送数据到服务器...");
			}
		} else {
			disabled(false);
			barGrid.setVisible(false);
			setMessageText("哎呀，没有文件操作啊...");
		}
	}

	private List<File> getFileList(ArrayList<File> list, File[] files) {
		File file = null;
		String name = null;

		for (int k = 0, ks = files.length; k < ks; k++) {
			file = files[k];
			name = file.getName().toLowerCase();

			if (file.isHidden() || name.endsWith("svn")) {
				continue;
			}

			if (file.isDirectory()) {
				list = (ArrayList<File>) getFileList(list, file.listFiles());
			}
			if (file.isFile()) {
				list.add(file);
			}
		}

		return list;
	}
	
	private List<File> getFileList(ArrayList<File> list, List<File> files) {
		File file = null;
		String name = null;

		for (int k = 0, ks = files.size(); k < ks; k++) {
			file = files.get(k);
			name = file.getName().toLowerCase();

			if (file.isHidden() || name.endsWith("svn")) {
				continue;
			}

			if (file.isDirectory()) {
				list = (ArrayList<File>) getFileList(list, file.listFiles());
			}
			if (file.isFile()) {
				list.add(file);
			}
		}

		return list;
	}

	private ComboBox<String> siteChooser() {
		ObservableList<String> _sites = FXCollections.observableList(Arrays
				.asList(siteList));
		siteCombo = new ComboBox<>(_sites);

		siteCombo.setValue(site);
		siteCombo.setPrefWidth(120);
		siteCombo.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0,
							String oldValue, String newValue) {
						// TODO Auto-generated method stub
						site = newValue;
						localRoot = MinaClientConfig.getStringValue("local."
								+ site);
						remoteRoot = MinaClientConfig.getStringValue("remote."
								+ site);

						L.info("FSC", FileClientUI.class, "siteChooser()",
								"INFO", "change site: " + oldValue + " => "
										+ newValue);
					}

				});

		return siteCombo;
	}

	private ComboBox<String> serverChooser() {
		ObservableList<String> _serverList = FXCollections
				.observableList(Arrays.asList(serverList));
		serverCombo = new ComboBox<>(_serverList);

		serverCombo.setValue(serverKey);
		serverCombo.setPrefWidth(200);
		serverCombo.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0,
							String oldValue, String newValue) {
						// TODO Auto-generated method stub
						serverKey = newValue;
						serverHost = MinaClientConfig.getStringValue("host."
								+ serverKey);
						serverPort = MinaClientConfig.getIntValue("port."
								+ serverKey);

						setNetStatutText("正在切换服务器...", "red");

						client.setServer(serverHost, serverPort);

						if (null != timer) {
							timer.cancel();
							timer = null;
						}
						if (client.isConnected()) {
							client.disconnect();
						}

						upload.setDisable(true);
						siteCombo.setDisable(true);
						browser.setDisable(true);
						clear.setDisable(true);
						table.setDisable(true);
						
						isRejected = false;
						setClientStatutText("切换", "black");
						setMessageText("");
						
						client.connect();

						netCheckTimer();

						L.info("FSC", FileClientUI.class, "serverChooser()",
								"INFO", "change server: " + oldValue + " => "
										+ newValue);
					}

				});

		return serverCombo;
	}
	
	private void insertFiles(){
		setMessageText("");

		if (null != fileList) {
			File file = null;

			barGrid.setVisible(true);

			for (int i = 0, size = fileList.size(); i < size; i++) {
				file = fileList.get(i);

				if (file.length() > max_length) { // 单个文件最大长度
					if (!ignoreList.contains(file)) {
						ignoreList.add(file);
					}
				} else {
					if (select_size < max_size) { // 最大文件数
						if (!allowList.contains(file)) {
							allowList.add(file);
							select_size++;
						}
					} else {
						waitingList.add(file);
					}
				}
			}

			insertTableViewData(table);

			int total = allowList.size();
			barLabel.setText("0/" + total);
			notify.setCount(0);
			notify.setTotal(total);

			bar.setProgress(0D);
			barGrid.setVisible(true);
		}
	}

	private HBox createHeader() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(12, 0, 12, 16));
		hbox.setSpacing(16);
		hbox.setStyle("" + "-fx-background-color: #E2E2E2; "
				+ "-fx-border-insets: 0 0 1 0; "
				+ "-fx-border-color: #E2E2E2 #E2E2E2 #A2A2A2 #E2E2E2; "
				+ "-fx-border-width: 0 0 1 0; " + "-fx-border-style: solid; "
				+ "-fx-font-size: 12px;");

		browser = new Button("选择文件");
		browser.setPrefSize(100, 20);
		browser.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// FileChooser fc = new FileChooser();
				// fileList = fc.showOpenMultipleDialog(null);
				JFileChooser chooser = new JFileChooser();

				File dir = new File(localRoot);
				if (dir.exists()) {
					chooser.setCurrentDirectory(dir);
				}

				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setMultiSelectionEnabled(true);

				int retValue = chooser.showOpenDialog(null);
				File[] tmps = null;

				if (retValue == JFileChooser.APPROVE_OPTION) {
					tmps = chooser.getSelectedFiles();

					fileList = getFileList(new ArrayList<File>(), tmps);
				}

				insertFiles();
			}
		});

		// ----------------------------------
		upload = new Button("上传文件 ");
		upload.setPrefSize(100, 20);
		upload.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				upload();
			}
		});

		// ----------------------------------
		clear = new Button("清空列表");
		clear.setPrefSize(100, 20);
		clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				fileList = null;
				allowList.clear();
				ignoreList.clear();
				waitingList.clear();
				select_size = 0;

				notify.setCount(0);
				notify.setTotal(0);

				// client.disconnect();

				// setNetStatutText("重置连接", "red");
				setClientStatutText("空闲", "black");
				setMessageText("");

				insertTableViewData(table);
				barLabel.setText("0/" + allowList.size());

				bar.setProgress(0D);
				barGrid.setVisible(false);
			}
		});

		// ----------------------------------
		barGrid = createProgressPane();
		// ----------------------------------
		hbox.getChildren().addAll(browser, upload, clear, serverChooser(), siteChooser(), barGrid);

		return hbox;
	}

	private GridPane createProgressPane() {
		GridPane grid = new GridPane();

		bar = createProgressBar();
		bar.setPrefSize(300, 20);
		// GridPane.setMargin(bar, new Insets(0, 0, 0, 180));
		grid.add(bar, 0, 0, 1, 1);

		barLabel = new Text("0/0");
		barLabel.autosize();
		barLabel.setFont(new Font("SimSun", 12));
		barLabel.setFontSmoothingType(FontSmoothingType.LCD);
		GridPane.setMargin(barLabel, new Insets(0, 5, 0, 5));
		grid.add(barLabel, 1, 0, 1, 1);

		// grid.setStyle("-fx-background-color:red");
		grid.setVisible(false);
		grid.setAlignment(Pos.CENTER_RIGHT);
		grid.setPrefSize(width - 548, 20);
		return grid;
	}

	private GridPane createStatusBar() {
		GridPane grid = new GridPane();
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		ColumnConstraints col3 = new ColumnConstraints();
		ColumnConstraints col4 = new ColumnConstraints();
		ColumnConstraints col5 = new ColumnConstraints();

		Font font = new Font("SimSun", 12);

		Separator s1 = new Separator(Orientation.VERTICAL);
		Separator s2 = new Separator(Orientation.VERTICAL);

		netStatusText = new Text("初始化");
		netStatusText.autosize();
		netStatusText.setFont(font);
		netStatusText.setFontSmoothingType(FontSmoothingType.LCD);

		s1.setPrefSize(1, 26);
		s2.setPrefSize(1, 26);

		transformText = new Text("空闲");
		transformText.autosize();
		transformText.setFont(font);
		transformText.setFontSmoothingType(FontSmoothingType.LCD);

		messageText = new Text("");
		messageText.autosize();
		messageText.setFont(font);
		messageText.setFontSmoothingType(FontSmoothingType.LCD);

		grid.addColumn(0, transformText);
		grid.addColumn(1, s1);
		grid.addColumn(2, netStatusText);
		grid.addColumn(3, s2);
		grid.addColumn(4, messageText);

		col1.setHalignment(HPos.LEFT);
		col1.setPrefWidth(60);
		col2.setHalignment(HPos.CENTER);
		col2.setPrefWidth(11);
		col3.setHalignment(HPos.LEFT);
		col3.setPrefWidth(240);
		col4.setHalignment(HPos.CENTER);
		col4.setPrefWidth(11);
		col5.setHalignment(HPos.LEFT);
		col5.setPrefWidth(width - 60 - 11 - 240 - 11);

		GridPane.setMargin(s1, new Insets(0, 5, 0, 5));
		GridPane.setMargin(s2, new Insets(0, 5, 0, 5));

		grid.setStyle("" + "-fx-background-color: #E2E2E2; "
				+ "-fx-border-insets: 1 0 0 0; "
				+ "-fx-border-color: #A2A2A2 #E2E2E2 #E2E2E2 #E2E2E2; "
				+ "-fx-border-width: 1 0 0 0; " + "-fx-border-style: solid; "
				+ "-fx-font-size: 12px;");

		grid.setPrefSize(width, 28);
		grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5);
		grid.setPadding(new Insets(3));
		grid.setAlignment(Pos.CENTER);

		return grid;
	}

	private TableView<FileInfo> createFileListTable() {
		table = new TableView<FileInfo>();

		table.setStyle("" + "-fx-border-insets: 0; " + "-fx-border-width: 0; "
				+ "-fx-font-size:12px;" + "-fx-background-color:#FCFCFC;");
		
		insertTableViewHeader(table);
		
		bindDragDropEvent(table);
		
		return table;
	}
	
	private void bindDragDropEvent(final TableView<FileInfo> table){
		
		Text text = new Text();
		
		text.setText("将文件拖拽到此区域");
		text.setFontSmoothingType(FontSmoothingType.LCD);		
		table.setPlaceholder(text);
				
		table.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Dragboard db = table.startDragAndDrop(TransferMode.ANY);	
				ClipboardContent cc = new ClipboardContent();
				
				List<File> flist = db.getFiles();
				cc.putFiles(flist);
				
				//flist = null;
				
				arg0.consume();
				
				L.info(FileClientUI.class, "setOnDragDetected()", "INFO", "Drag Detected...");
			}
			
		});
		
		table.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent arg0) {
				// TODO Auto-generated method stub
				Dragboard db = arg0.getDragboard();
				
				if(db.hasFiles()){
					arg0.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
                
                arg0.consume();
			}
		});
		
		table.setOnDragEntered(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent arg0) {
				// TODO Auto-generated method stub
				fileList = null;
				
				Dragboard db = arg0.getDragboard();
				List<File> flist = db.getFiles();
				fileList = getFileList(new ArrayList<File>(), flist);
				
				L.info(FileClientUI.class, "setOnDragEntered()", "INFO", "Drag Entered...");
				
				arg0.consume();
			}
		});
		
		table.setOnDragExited(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent arg0) {
				// TODO Auto-generated method stub
				//insertFiles();
				arg0.consume();
				
				fileList = null;
				L.info(FileClientUI.class, "setOnDragExited()", "INFO", "Drag Exited...");		
			}
		});
				

		table.setOnDragDropped(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent arg0) {
				// TODO Auto-generated method stub
				boolean completed = false;
				
				Dragboard db = arg0.getDragboard();
				
				if(db.hasFiles()){
					insertFiles();
					completed = true;
				}
				
				arg0.setDropCompleted(completed);
				arg0.consume();
				L.info(FileClientUI.class, "setOnDragDropped()", "INFO", "Drag Droped...");
			}
		});
		
	}

	@SuppressWarnings("unchecked")
	private void insertTableViewHeader(TableView<FileInfo> table) {
		TableColumn<FileInfo, String> status = new TableColumn<FileInfo, String>(
				"Status");
		TableColumn<FileInfo, String> fileName = new TableColumn<FileInfo, String>(
				"File Name");
		TableColumn<FileInfo, String> localPath = new TableColumn<FileInfo, String>(
				"Local Path");
		TableColumn<FileInfo, String> remotePath = new TableColumn<FileInfo, String>(
				"Remote Path");
		TableColumn<FileInfo, String> fileSize = new TableColumn<FileInfo, String>(
				"Size(bytes)");
		TableColumn<FileInfo, String> lastModified = new TableColumn<FileInfo, String>(
				"Last Modified");

		status.setCellValueFactory(new PropertyValueFactory<FileInfo, String>(
				"status"));
		fileName.setCellValueFactory(new PropertyValueFactory<FileInfo, String>(
				"fileName"));
		localPath
				.setCellValueFactory(new PropertyValueFactory<FileInfo, String>(
						"localPath"));
		remotePath
				.setCellValueFactory(new PropertyValueFactory<FileInfo, String>(
						"remotePath"));
		fileSize.setCellValueFactory(new PropertyValueFactory<FileInfo, String>(
				"fileSize"));
		lastModified
				.setCellValueFactory(new PropertyValueFactory<FileInfo, String>(
						"lastModified"));

		status.setPrefWidth(60);
		fileName.setPrefWidth(180);
		localPath.setPrefWidth(270);
		remotePath.setPrefWidth(270);
		fileSize.setPrefWidth(90);
		lastModified.setPrefWidth(140);

		status.setCellFactory(new CellFactory(Pos.CENTER));

		fileSize.setCellFactory(new CellFactory(Pos.CENTER_RIGHT));

		lastModified.setCellFactory(new CellFactory(Pos.CENTER));

		table.getColumns().addAll(status, fileName, localPath, remotePath,
				fileSize, lastModified);
	}

	private String getRelativeDir(String parentDir) {
		String relativeDir = "";
		parentDir = parentDir.replaceAll("\\\\", "/");

		if (!parentDir.endsWith("/")) {
			parentDir += "/";
		}

		System.out.println("parentDir: " + parentDir.toLowerCase());
		System.out.println("localRoot: " + localRoot.toLowerCase());
		System.out.println(parentDir.startsWith(localRoot));

		if ((parentDir.toLowerCase()).startsWith(localRoot.toLowerCase())) {
			relativeDir = parentDir.substring(localRoot.length());
		} else if (parentDir.startsWith("/")) {
			relativeDir = parentDir.substring(1);
		} else {
			relativeDir = "unknown/" + dateString;
		}
		L.info("FSC", FileClientUI.class, "getRelativeDir()", "INFO",
				"parent dir is " + parentDir);
		L.info("FSC", FileClientUI.class, "getRelativeDir()", "INFO",
				"relative dir is " + relativeDir);

		return relativeDir;
	}

	private FileInfo newFileInfo(File file, String status) {
		FileInfo info = new FileInfo();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		NumberFormat nf = NumberFormat.getInstance();
		String parentDir = "";
		String relativeDir = "";

		parentDir = file.getParent();
		relativeDir = getRelativeDir(parentDir);

		info.setFileName(file.getName());
		info.setLocalPath(file.getParent());
		info.setRemotePath(remoteRoot + relativeDir);
		info.setFileSize(nf.format(file.length()));
		info.setLastModified(df.format(new Date(file.lastModified())));
		info.setStatus(status);

		return info;

	}

	private void insertTableViewData(TableView<FileInfo> table) {
		ObservableList<FileInfo> obList = null;
		ArrayList<FileInfo> list = new ArrayList<FileInfo>();
		File file = null;

		for (int i = 0, size = allowList.size(); i < size; i++) {
			file = allowList.get(i);

			list.add(newFileInfo(file, "Allow"));
		}

		for (int i = 0, size = waitingList.size(); i < size; i++) {
			file = waitingList.get(i);

			list.add(newFileInfo(file, "Waiting"));
		}

		for (int i = 0, size = ignoreList.size(); i < size; i++) {
			file = ignoreList.get(i);

			list.add(newFileInfo(file, "Deny"));
		}

		file = null;

		obList = FXCollections.observableArrayList(list);

		table.setItems(obList);

	}

	private ProgressBar createProgressBar() {
		ProgressBar pb = new ProgressBar();
		pb.setProgress(0.0D);

		return pb;
	}

	private void setNetStatutText(String text, String colorStyle) {
		netStatusText.setText(text);
		netStatusText.setFill(Color.web(colorStyle));
	}

	private void setClientStatutText(String text, String colorStyle) {
		transformText.setText(text);
		transformText.setFill(Color.web(colorStyle));
	}

	private void setMessageText(String text) {
		messageText.setText(text);
	}

	private void delay(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void netCheckTimer() {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (!isRejected) {
					if (!client.isConnected()) {
						setNetStatutText("与服务器断开：" + serverHost + ":"
								+ serverPort, "red");
						delay(300);
						setNetStatutText("尝试重新连接：" + serverHost + ":"
								+ serverPort, "red");
						delay(300);
						client.connect();						
					} else {
						setNetStatutText("连接成功：" + serverHost + ":"
								+ serverPort, "black");
						setClientStatutText("空闲", "black");
						disabled(false);
					}
				}
			}
		}, 5000, 8000);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox header = createHeader();
		TableView<FileInfo> table = createFileListTable();
		
		Parent root = BorderPaneBuilder.create()
				.top(header)
				.center(table)
				.bottom(createStatusBar())
				.build();

		Scene scene = new Scene(root, width, height);

		primaryStage.setScene(scene);
		primaryStage.setTitle("JFX - File Server Client(FSC)/1.0.1 - MINA");
		primaryStage.setResizable(false);
		primaryStage.show();

		notify.set_bar(bar);
		notify.set_label(barLabel);

		client.injectNotify(notify);

		client.setServer(serverHost, serverPort);
		client.connect();

		netCheckTimer();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				if (null != timer) {
					timer.cancel();
					timer = null;
				}
				if (client.isConnected()) {
					client.disconnect();
				}
			}

		});
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
