#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QFileDialog>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:
    void on_fileOpenButton_clicked();

    void on_fileOpenButton_3_clicked();

    void on_fileOpenButton_2_clicked();

private:
    Ui::MainWindow *ui;
};

#endif // MAINWINDOW_H
