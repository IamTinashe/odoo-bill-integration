# Odoo-Bill Integration
**Seemless synchronization**

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## Overview

Odoo-Bill Integration is an open-source Java application that automates the synchronization of invoices from [Bill.com](https://www.bill.com/) to the [Odoo ERP](https://www.odoo.com/) system. By eliminating manual data entry, it saves time and reduces errors for accounts receivable teams handling large volumes of invoices.

## Features

- **Automatic Invoice Synchronization**: Fetches invoices from Bill.com and imports them into Odoo ERP.
- **Customer Data Mapping**: Associates invoices with the correct customers by mapping customer IDs and names.
- **Error Handling and Logging**: Provides robust error handling and detailed logging for troubleshooting.
- **Scalable**: Capable of handling over 400 invoices per month efficiently.
- **Open-Source**: Freely available for customization and enhancement by the community.

## Getting Started

These instructions will help you set up the application on your local machine for development and testing purposes.

### Prerequisites

- **Java 11** or higher
- **Maven 3.6+**
- **Docker** (optional, for containerization)
- **Bill.com API Credentials**: You need a valid Bill.com account and API credentials.
- **Odoo ERP Instance and Credentials**: Access to an Odoo ERP system with necessary permissions.

### Installation

#### 1. Clone the Repository

```bash
git clone https://github.com/IamTinashe/odoo-bill-integration.git
cd odoo-bill-integration

