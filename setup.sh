#!/bin/bash

# Скачиваем архив проекта с GitHub
wget https://github.com/perminand/later/archive/main.zip

# Распаковываем архив
unzip main.zip

# Переходим в директорию проекта
cd later-main

# Запускаем скрипт настройки среды
./setup.sh

# Запускаем приложение
./app
