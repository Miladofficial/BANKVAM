# وام من - BANKVAM

![Version](https://img.shields.io/badge/version-1.0.0-blue)
![Min SDK](https://img.shields.io/badge/minSDK-29-green)
![Language](https://img.shields.io/badge/language-Kotlin-purple)

## 📱 درباره اپلیکیشن

**وام من** یک اپلیکیشن اندروید حرفه‌ای برای مدیریت جامع وام‌ها و تسهیلات بانکی ایران است.

## ✨ ویژگی‌های اصلی

- 📊 داشبورد جامع با آمار و نمودارها
- 🏦 پشتیبانی از تمام بانک‌های ایران
- 📅 تقویم شمسی با یادآوری اقساط
- 📈 نمودارهای تحلیلی و گزارشات
- 💾 خروجی Excel و PDF
- 🔔 یادآوری‌های هوشمند برای اقساط
- 🇮🇷 رابط کاربری کاملاً فارسی
- 📱 طراحی موبایل‌فرست
- 🔐 حریم‌خصوصی تضمین‌شده (بدون ارسال داده به سرور)

## 🏗️ معماری

- **MVVM + Clean Architecture**
- **Jetpack Compose** برای UI
- **Room Database** برای ذخیره‌سازی
- **Hilt** برای Dependency Injection
- **Kotlin Coroutines & Flow** برای عملیات غیرهمزمان
- **WorkManager** برای یادآوری‌های پس‌زمینه

## 📋 صفحات اپلیکیشن

1. **داشبورد** - خلاصه وام‌ها و نمودارها
2. **لیست وام‌ها** - مشاهده و جستجوی وام‌ها
3. **افزودن/ویرایش وام** - ثبت وام جدید
4. **جزئیات وام** - اطلاعات کامل و جدول اقساط
5. **تقویم** - سررسید اقساط
6. **گزارشات** - آمار و نمودارها
7. **تنظیمات** - تم، پشتیبان‌گیری، تنظیمات

## 📦 فناوری‌های استفاده‌شده

- **Kotlin** - زبان برنامه‌نویسی
- **Jetpack Compose** - UI Framework
- **Room** - پایگاه داده محلی
- **Hilt** - تزریق وابستگی‌ها
- **Coroutines** - برنامه‌نویسی ناهمزمان
- **Flow** - جریان داده‌های راکتیو
- **WorkManager** - کارهای زمان‌بندی‌شده
- **Material Design 3** - طراحی رابط

## 📊 ساختار پروژه

```
app/src/main/
├── java/com/miladofficial/vamtracker/
│   ├── data/
│   │   ├── local/
│   │   │   ├── dao/
│   │   │   │   └── VamDao.kt
│   │   │   ├── database/
│   │   │   │   └── VamDatabase.kt
│   │   │   └── entity/
│   │   │       └── VamEntity.kt
│   │   ├── repository/
│   │   │   ├── VamRepository.kt
│   │   │   └── VamRepositoryImpl.kt
│   │   └── worker/
│   │       └── KargarYadavari.kt
│   ├── domain/
│   │   ├── model/
│   │   │   └── VamModel.kt
│   │   └── usecase/
│   │       └── MohasebeVam.kt
│   ├── di/
│   │   └── AppModule.kt
│   ├── presentation/
│   │   ├── viewmodel/
│   │   │   ├── DashboardViewModel.kt
│   │   │   ├── VamListViewModel.kt
│   │   │   └── AddEditVamViewModel.kt
│   │   └── ui/
│   │       ├── screens/
│   │       │   ├── DashboardScreen.kt
│   │       │   ├── VamListScreen.kt
│   │       │   ├── AddEditVamScreen.kt
│   │       │   ├── VamDetailsScreen.kt
│   │       │   ├── CalendarScreen.kt
│   │       │   ├── ReportsScreen.kt
│   │       │   └── SettingsScreen.kt
│   │       ├── navigation/
│   │       │   └── Navigation.kt
│   │       └── App.kt
│   └── VamTrackerApplication.kt
└── res/
    └── values/
        ├── strings.xml
        ├── colors.xml
        ├── dimens.xml
        └── themes.xml
```

## 🚀 شروع به کار

### پیش‌نیاز‌ها

- Android Studio 2022.1 یا بالاتر
- JDK 11 یا بالاتر
- Gradle 7.0+
- Android SDK 29+

### نصب و اجرا

```bash
# Clone کردن مخزن
git clone https://github.com/Miladofficial/BANKVAM.git
cd BANKVAM

# بناء و اجرا
./gradlew build
./gradlew installDebug
```

## 🎯 فرمول‌های مالی

### محاسبه قسط ماهانه (وام چسبانده)
```
M = P × r(1+r)^n / ((1+r)^n - 1)

M: قسط ماهانه
P: مبلغ اصل وام
r: نرخ سود ماهانه (سالانه/12/100)
n: تعداد اقساط
```

### محاسبه سود ماهانه
```
Interest = Principal × (Annual Rate / 12 / 100)
```

### محاسبه مانده بدهی
```
Remaining = Principal - Principal Paid + Accrued Interest
```

## 🎨 رنگ‌های کاربردی

| رنگ | کد | معنی |
|-----|-----|------|
| 🔵 آبی | #2196F3 | اصلی |
| 🔴 قرمز | #F44336 | معوق / بحرانی |
| 🟠 نارنجی | #FF9800 | ۳ روز مانده |
| 🟡 زرد | #FFC107 | ۷ روز مانده |
| 🟢 سبز | #4CAF50 | تکمیل شده |

## 🔔 سیستم یادآوری

یادآوری‌های هوشمند برای اقساط:

- **قرمز فوری**: اقساط معوق
- **قرمز**: اقساط امروز
- **نارنجی**: اقساط ۳ روز دیگر
- **زرد**: اقساط ۷ روز دیگر

## 🔐 حریم خصوصی و امنیت

✅ **تمام اطلاعات محلی ذخیره می‌شوند**
✅ **بدون ارتباط با سرور**
✅ **بدون جمع‌آوری داده‌های شخصی**
✅ **بدون تبلیغات**

## 📊 بانک‌های پشتیبانی‌شده

- بانک ملی ایران
- بانک ملت
- بانک صادرات ایران
- بانک تجارت
- بانک رفاه کارگران
- بانک مسکن
- بانک کشاورزی
- بانک سپه
- بانک آینده
- بانک پاسارگاد
- بانک اقتصاد نوین
- بانک سامان
- بانک سینا
- بانک شهر
- بانک دی
- قرض‌الحسنه مهر ایران
- قرض‌الحسنه رسالت
- موسسه اعتباری ملل
- موسسه اعتباری نور
- و سایر بانک‌ها

## 📝 انواع وام‌های پشتیبانی‌شده

- قرض‌الحسنه
- وام ازدواج
- وام فرزندآوری
- وام مسکن
- وام خودرو
- وام لوازم خانگی
- وام کسب‌وکار
- وام حقوق
- سهام عدالت
- و بیشتر...

## 🐛 گزارش مشکلات

برای گزارش مشکلات یا پیشنهادات:
- 📧 [GitHub Issues](https://github.com/Miladofficial/BANKVAM/issues)

## 📄 لایسنس

این پروژه تحت لایسنس **MIT** منتشر شده است.

```
MIT License

Copyright (c) 2024 Milad

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

## 👨‍💻 توسعه‌دهنده

**میلاد**
- GitHub: [@Miladofficial](https://github.com/Miladofficial)
- Email: 63480606+Miladofficial@users.noreply.github.com

## 🙏 سپاس‌گزاری

تشکر از:
- تیم Jetpack
- تیم Kotlin
- تمام مساهمین

## 📞 تماس و پشتیبانی

برای پرسش‌ها یا پیشنهادات:
- 📧 GitHub Discussions
- 🐛 GitHub Issues

---

**نسخه:** 1.0.0  
**آخرین به‌روزرسانی:** 1403/03/16 (۲۰۲۴/۰۶/۰۵)  
**وضعیت:** 🟢 فعال و در حال توسعه

**ساخته شده با ❤️ برای ایران**
