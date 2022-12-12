// XplanePlugin.h : main header file for the XplanePlugin DLL
//

#pragma once

#ifndef __AFXWIN_H__
	#error "include 'stdafx.h' before including this file for PCH"
#endif

#include "resource.h"		// main symbols


// CXplanePluginApp
// See XplanePlugin.cpp for the implementation of this class
//

class CXplanePluginApp : public CWinApp
{
public:
	CXplanePluginApp();

// Overrides
public:
	virtual BOOL InitInstance();

	DECLARE_MESSAGE_MAP()
};
