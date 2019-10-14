/* Generated by CIL v. 1.3.6 */
/* print_CIL_Input is true */

int assert_1 = 0;
int m_pc  =    0;
int t1_pc  =    0;
int t2_pc  =    0;
int t3_pc  =    0;
int m_st  ;
int t1_st  ;
int t2_st  ;
int t3_st  ;
int m_i  ;
int t1_i  ;
int t2_i  ;
int t3_i  ;
int M_E  =    2;
int T1_E  =    2;
int T2_E  =    2;
int T3_E  =    2;
int E_1  =    2;
int E_2  =    2;
int E_3  =    2;
int is_master_triggered() ;
int is_transmit1_triggered() ;
int is_transmit2_triggered() ;
int is_transmit3_triggered() ;
void immediate_notify() ;
void master() 
{ 

  {
  if (m_pc == 0) {
    goto M_ENTRY;
  } else {
    if (m_pc == 1) {
      goto M_WAIT;
    } 
  }
  M_ENTRY: ;
  {
  while (1 < 2) {
   // while_0_continue: /* CIL Label */ ;
    {
    E_1 = 1;
    immediate_notify();
    E_1 = 2;
    }
    {
    while (1 < 2) {
    //  while_1_continue: /* CIL Label */ ;
      m_pc = 1;
      m_st = 2;

      goto return_label;
      M_WAIT: ;
    }
    while_1_break: /* CIL Label */ ;
    }
  }
  while_0_break: /* CIL Label */ ;
  }

  return_label:; /* CIL Label */ 
  
}
}
void transmit1() 
{ 

  {
  if (t1_pc == 0) {
    goto T1_ENTRY;
  } else {
    if (t1_pc == 1) {
      goto T1_WAIT;
    } 
  }
  T1_ENTRY: ;
  {
  while (1 < 2) {
   // while_2_continue: /* CIL Label */ ;
    t1_pc = 1;
    t1_st = 2;

    goto return_label;
    T1_WAIT: 
    {
    E_2 = 1;
    immediate_notify();
    E_2 = 2;
    }
  }
  while_2_break: /* CIL Label */ ;
  }

  return_label:; /* CIL Label */ 
  
}
}
void transmit2() 
{ 

  {
  if (t2_pc == 0) {
    goto T2_ENTRY;
  } else {
    if (t2_pc == 1) {
      goto T2_WAIT;
    } 
  }
  T2_ENTRY: ;
  {
  while (1 < 2) {
    //while_3_continue: /* CIL Label */ ;
    t2_pc = 1;
    t2_st = 2;

    goto return_label;
    T2_WAIT: 
    {
    E_3 = 1;
    immediate_notify();
    E_3 = 2;
    }
  }
  while_3_break: /* CIL Label */ ;
  }

  return_label:; /* CIL Label */ 
  
}
}

void transmit3() 
{ 

  {
  if (t3_pc == 0) {
    goto T3_ENTRY;
  } else {
    if (t3_pc == 1) {
      goto T3_WAIT;
    } 
  }
  T3_ENTRY: ;
  {
  while (1 < 2) {
   // while_4_continue: /* CIL Label */ ;
    t3_pc = 1;
    t3_st = 2;

    goto return_label;
    T3_WAIT: 
    {
    assert_1 = 1;
    }
  }
  while_4_break: /* CIL Label */ ;
  }

  return_label:; /* CIL Label */ 
  
}
}
int is_master_triggered() 
{ int __retres1 ;

  {
  if (m_pc == 1) {
    if (M_E == 1) {
      __retres1 = 1;
      goto return_label;
    } 
  } 
  __retres1 = 0;
  return_label:; /* CIL Label */ 
  return (__retres1);
}
}
int is_transmit1_triggered() 
{ int __retres1 ;

  {
  if (t1_pc == 1) {
    if (E_1 == 1) {
      __retres1 = 1;
      goto return_label;
    } 
  } 
  __retres1 = 0;
  return_label:; /* CIL Label */ 
  return (__retres1);
}
}
int is_transmit2_triggered() 
{ int __retres1 ;

  {
  if (t2_pc == 1) {
    if (E_2 == 1) {
      __retres1 = 1;
      goto return_label;
    } 
  } 
  __retres1 = 0;
  return_label:; /* CIL Label */ 
  return (__retres1);
}
}
int is_transmit3_triggered() 
{ int __retres1 ;

  {
  if (t3_pc == 1) {
    if (E_3 == 1) {
      __retres1 = 1;
      goto return_label;
    }
  } 
  __retres1 = 0;
  return_label:; /* CIL Label */ 
  return (__retres1);
}
}
void update_channels() 
{ 

  {

  
}
}
void init_threads() 
{ 

  {
  if (m_i == 1) {
    m_st = 0;
  } else {
    m_st = 2;
  }
  if (t1_i == 1) {
    t1_st = 0;
  } else {
    t1_st = 2;
  }
  if (t2_i == 1) {
    t2_st = 0;
  } else {
    t2_st = 2;
  }
  if (t3_i == 1) {
    t3_st = 0;
  } else {
    t3_st = 2;
  }

  
}
}
int exists_runnable_thread() 
{ int __retres1 ;

  {
  if (m_st == 0) {
    __retres1 = 1;
    goto return_label;
  } else {
    if (t1_st == 0) {
      __retres1 = 1;
      goto return_label;
    } else {
      if (t2_st == 0) {
        __retres1 = 1;
        goto return_label;
      } else {
        if (t3_st == 0) {
          __retres1 = 1;
          goto return_label;
        }
      }
    }
  }
  __retres1 = 0;
  return_label:; /* CIL Label */ 
  return (__retres1);
}
}
void eval() 
{ int __NONDET ;
  int tmp ;

  {
  {
  while (1 < 2) {
   // while_5_continue: /* CIL Label */ ;
    {
    tmp = exists_runnable_thread();
    }
    if (tmp) {

    } else {
      goto while_5_break;
    }
    if (m_st == 0) {
      if (__NONDET) {
        {
        m_st = 1;
        master();
        }
      } 
    } 
    if (t1_st == 0) {
      if (__NONDET) {
        {
        t1_st = 1;
        transmit1();
        }
      } 
    } 
    if (t2_st == 0) {
      if (__NONDET) {
        {
        t2_st = 1;
        transmit2();
        }
      } 
    } 
    if (t3_st == 0) {
      if (__NONDET) {
        {
        t3_st = 1;
        transmit3();
        }
      } 
    } 
  }
  while_5_break: /* CIL Label */ ;
  }

  
}
}
void fire_delta_events() 
{ 

  {
  if (M_E == 0) {
    M_E = 1;
  } 
  if (T1_E == 0) {
    T1_E = 1;
  } 
  if (T2_E == 0) {
    T2_E = 1;
  } 
  if (T3_E == 0) {
    T3_E = 1;
  } 
  if (E_1 == 0) {
    E_1 = 1;
  } 
  if (E_2 == 0) {
    E_2 = 1;
  } 
  if (E_3 == 0) {
    E_3 = 1;
  } 
}
}
void reset_delta_events() 
{ 

  {
  if (M_E == 1) {
    M_E = 2;
  } 
  if (T1_E == 1) {
    T1_E = 2;
  } 
  if (T2_E == 1) {
    T2_E = 2;
  }
  if (T3_E == 1) {
    T3_E = 2;
  } 
  if (E_1 == 1) {
    E_1 = 2;
  } 
  if (E_2 == 1) {
    E_2 = 2;
  }
  if (E_3 == 1) {
    E_3 = 2;
  } 

  
}
}
void activate_threads() 
{ int tmp ;
  int tmp___0 ;
  int tmp___1 ;
  int tmp___2 ;

  {
  {
  tmp = is_master_triggered();
  }
  if (tmp) {
    m_st = 0;
  } 
  {
  tmp___0 = is_transmit1_triggered();
  }
  if (tmp___0) {
    t1_st = 0;
  } 
  {
  tmp___1 = is_transmit2_triggered();
  }
  if (tmp___1) {
    t2_st = 0;
  } 
  {
  tmp___2 = is_transmit3_triggered();
  }
  if (tmp___2) {
    t3_st = 0;
  } 

  
}
}
void immediate_notify() 
{ 

  {
  {
  activate_threads();
  }

  
}
}
void fire_time_events() 
{ 

  {
  M_E = 1;

  
}
}
void reset_time_events() 
{ 

  {
  if (M_E == 1) {
    M_E = 2;
  }
  if (T1_E == 1) {
    T1_E = 2;
  } 
  if (T2_E == 1) {
    T2_E = 2;
  } 
  if (T3_E == 1) {
    T3_E = 2;
  } 
  if (E_1 == 1) {
    E_1 = 2;
  } 
  if (E_2 == 1) {
    E_2 = 2;
  } 
  if (E_3 == 1) {
    E_3 = 2;
  } 
  
}
}
void init_model() 
{ 

  {
  m_i = 1;
  t1_i = 1;
  t2_i = 1;
  t3_i = 1;

  
}
}
int stop_simulation() 
{ int tmp ;
  int __retres2 ;

  {
  {
  tmp = exists_runnable_thread();
  }
  if (tmp) {
    __retres2 = 0;
    goto return_label;
  } 
  __retres2 = 1;
  return_label:; /* CIL Label */ 
  return (__retres2);
}
}
void start_simulation() 
{ int kernel_st ;
  int tmp ;
  int tmp___0 ;

  {
  {
  kernel_st = 0;
  update_channels();
  init_threads();
  fire_delta_events();
  activate_threads();
  reset_delta_events();
  }
  {
  while (1 < 2) {
   // while_6_continue: /* CIL Label */ ;
    {
    kernel_st = 1;
    eval();
    }
    {
    kernel_st = 2;
    update_channels();
    }
    {
    kernel_st = 3;
    fire_delta_events();
    activate_threads();
    reset_delta_events();
    }
    {
    tmp = exists_runnable_thread();
    }
    if (tmp == 0) {
      {
      kernel_st = 4;
      fire_time_events();
      activate_threads();
      reset_time_events();
      }
    } 
    {
    tmp___0 = stop_simulation();
    }
    if (tmp___0) {
      goto while_6_break;
    } 
  }
  while_6_break: /* CIL Label */ ;
  }

  
}
}
int transmitter_3() 
{ int __retres1 ;

  {
  {
  init_model();
  start_simulation();
  }
  __retres1 = assert_1;
  return (__retres1);
}
}