<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>오늘의 날씨</title>
</head>
<body>
<h2>🌤️ ${city}의 현재 날씨</h2>

<ul>
    <li>🌡️ 기온: ${weather.main.temp}°C</li>
    <li>💧 습도: ${weather.main.humidity}%</li>
    <li>📋 상태: ${weather.weather[0].description}</li>
    <li>🖼️ 아이콘: <img src="${iconUrl}" alt="날씨 아이콘"></li>
</ul>
</body>
</html>